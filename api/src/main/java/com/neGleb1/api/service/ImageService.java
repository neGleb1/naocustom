package com.neGleb1.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.neGleb1.api.config.ImageStorageConfig;
import com.neGleb1.api.exception.ImageNotFoundException;
import com.neGleb1.api.exception.StorageException;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class ImageService {
    
    private final Path rootLocation;
	private final String hostLocation;

	@Autowired
	public ImageService(ImageStorageConfig properties) {
		this.rootLocation = Paths.get(properties.getLocation());
		this.hostLocation = properties.getHost();
	}

    public String store(MultipartFile file) {
		final String imageName =LocalDate.now() + UUID.randomUUID().toString() + ".jpg";
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			Path destinationFile = this.rootLocation.resolve(Paths.get(imageName)).normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageException("Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Thumbnails.of(inputStream).forceSize(480, 640).outputFormat("jpg").toFile(destinationFile.toFile());
			}
			return this.hostLocation+imageName;
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	public Map<String, String> storeAsThumbnail(MultipartFile file) {
		Map<String, String> paths = new HashMap<String, String>();
		final String thumbnailName ="thumbnail-" + LocalDate.now() + UUID.randomUUID().toString() + ".jpg";
		final String imageName =LocalDate.now() + UUID.randomUUID().toString() + ".jpg";
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			Path thumbnailFile = this.rootLocation.resolve(Paths.get(thumbnailName)).normalize().toAbsolutePath();
			Path imageFile = this.rootLocation.resolve(Paths.get(imageName)).normalize().toAbsolutePath();

			if (!imageFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageException("Cannot store file outside current directory.");
			}

			try (InputStream inputStream = file.getInputStream()) {
				Thumbnails.of(inputStream).forceSize(300, 300).outputFormat("jpg").toFile(thumbnailFile.toFile());
			}
			try (InputStream inputStream = file.getInputStream()) {
				Thumbnails.of(inputStream).forceSize(480, 640).outputFormat("jpg").toFile(imageFile.toFile());
			}
			paths.put("thumbnail", this.hostLocation + thumbnailName);
			paths.put("image", this.hostLocation + imageName);
			return paths;
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new ImageNotFoundException("Could not read file: " + filename);
			}
		}
		catch (MalformedURLException e) {
			throw new ImageNotFoundException("Could not find image: " + filename, e);
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
}
