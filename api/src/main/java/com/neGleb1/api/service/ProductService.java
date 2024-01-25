package com.neGleb1.api.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neGleb1.api.dao.ImageDao;
import com.neGleb1.api.dao.ProductDao;
import com.neGleb1.api.dto.NewProductRequest;
import com.neGleb1.api.dto.ProductCardResponse;
import com.neGleb1.api.dto.ProductItemResponse;
import com.neGleb1.api.dto.UpdateProductRequest;
import com.neGleb1.api.model.Image;
import com.neGleb1.api.model.Product;
import com.neGleb1.api.model.ProductAttributes;

@Service
public class ProductService {
    
    private final ProductDao productDao;
    private final ImageDao imageDao;
    private final ImageService imageService;

    public ProductService(ProductDao productDao, ImageDao imageDao, ImageService imageService) {
        this.productDao = productDao;
        this.imageDao = imageDao;
        this.imageService = imageService;
    }

    public List<ProductItemResponse> getAllProducts(){
        return productDao.selectAll();
    }

    public ProductCardResponse getProduct(long id){

        ProductItemResponse product = productDao.select(id);
        ProductAttributes attr = productDao.selectAttributesById(id);
        Image[] images = imageDao.selectAllByProductId(id).toArray(new Image[0]);
        ProductCardResponse response = new ProductCardResponse();

        response.setProductId(product.getProductId());
        response.setMerchantId(product.getMerchantId());
        response.setProductName(product.getProductName());
        response.setMerchantName(product.getMerchantName());
        response.setSex(product.getSex());
        response.setPrice(product.getPrice());
        response.setCategory(product.getCategory());
        response.setColor(product.getColor());
        response.setSize(product.getSize());
        response.setBrand(product.getBrand());
        response.setMaterial(product.getMaterial());
        response.setDescription(attr.getDescription());
        response.setTimestamp(product.getTimestamp());
        response.setImages(images);

        return response;
    }

    public List<ProductItemResponse> getAllProductsByQuery(String query){
        return productDao.selectAllByQuery(query);
    }

    public List<ProductItemResponse> getAllProductsBySexAndCategory(String sex, String category){
        return productDao.selectAllBySexAndCategory(sex, category);
    }

    public List<ProductItemResponse> getAllProductsByMerchantId(long id){
        return productDao.selectAllByMerchantId(id);
    }

    public void addProduct(NewProductRequest newProduct, MultipartFile file){

        Product product = new Product();
        product.setMerchantId(newProduct.getMerchantId());
        product.setProductName(newProduct.getProductName());
        product.setSex(newProduct.getSex());
        product.setCategory(newProduct.getCategory());
        product.setPrice(newProduct.getPrice());
        product.setIsAvailable(true);
        product.setTimestamp(ZonedDateTime.now().toString());
        long id = productDao.save(product);

        ProductAttributes attr = new ProductAttributes();
        attr.setProductId(id);
        attr.setBrand(newProduct.getBrand());
        attr.setColor(newProduct.getColor());
        attr.setDescription(newProduct.getDescription());
        attr.setMaterial(newProduct.getMaterial());
        attr.setSize(newProduct.getSize());
        productDao.saveProductAttributes(attr);

        Map<String, String> imagePathsMap = imageService.storeAsThumbnail(file);

        Image thumbnail = new Image();
        thumbnail.setProductId(id);
        thumbnail.setIsThumbnail(true);
        thumbnail.setPath(imagePathsMap.get("thumbnail"));
        thumbnail.setTimestamp(ZonedDateTime.now().toString());

        Image image = new Image();
        image.setProductId(id);
        image.setIsThumbnail(false);
        image.setPath(imagePathsMap.get("image"));
        image.setTimestamp(ZonedDateTime.now().toString());

        imageDao.save(thumbnail);
        imageDao.save(image);
    }

    public void deleteProduct(long id){
        productDao.delete(id);
    }

    public void updateProduct(
        UpdateProductRequest product, 
        MultipartFile thumbnail,
        MultipartFile image1, 
        MultipartFile image2, 
        MultipartFile image3
    ){
        Product updatedProduct = new Product();
        updatedProduct.setProductId(product.getProductId());
        updatedProduct.setProductName(product.getProductName());
        updatedProduct.setMerchantId(product.getMerchantId());
        updatedProduct.setCategory(product.getCategory());
        updatedProduct.setIsAvailable(true);
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setSex(product.getSex());
        productDao.update(updatedProduct);

        ProductAttributes updatedAttributes = new ProductAttributes();
        updatedAttributes.setProductId(product.getProductId());
        updatedAttributes.setColor(product.getColor());
        updatedAttributes.setBrand(product.getBrand());
        updatedAttributes.setSize(product.getSize());
        updatedAttributes.setDescription(product.getDescription());
        updatedAttributes.setMaterial(product.getMaterial());
        productDao.updateProductAttributes(updatedAttributes);

        String[] oldImages = product.getOldImages();

        if (thumbnail != null) {
            ProductItemResponse productWithThumbnail = productDao.select(product.getProductId());
            imageDao.delete(productWithThumbnail.getThumbnailPath());
            imageDao.delete(oldImages[0]);
            Map<String, String> imagePathsMap = imageService.storeAsThumbnail(thumbnail);
            Image updatedThumbnail = new Image();
            updatedThumbnail.setProductId(product.getProductId());
            updatedThumbnail.setIsThumbnail(true);
            updatedThumbnail.setPath(imagePathsMap.get("thumbnail"));
            updatedThumbnail.setTimestamp(ZonedDateTime.now().toString());

            Image image = new Image();
            image.setProductId(product.getProductId());
            image.setIsThumbnail(false);
            image.setPath(imagePathsMap.get("image"));
            image.setTimestamp(ZonedDateTime.now().toString());

            imageDao.save(updatedThumbnail);
            imageDao.save(image);
        }

        if (image1 != null) {
            if (oldImages.length >= 2) {
                imageDao.delete(oldImages[1]);
            }
            String path1 = imageService.store(image1);
            Image updatedImage1 = new Image();
            updatedImage1.setProductId(product.getProductId());
            updatedImage1.setIsThumbnail(true);
            updatedImage1.setPath(path1);
            updatedImage1.setTimestamp(ZonedDateTime.now().toString());
            imageDao.save(updatedImage1);
        }

        if (image2 != null) {
            if (oldImages.length >= 3) {
                imageDao.delete(oldImages[2]);
            }
            String path2 = imageService.store(image2);
            Image updatedImage2 = new Image();
            updatedImage2.setProductId(product.getProductId());
            updatedImage2.setIsThumbnail(true);
            updatedImage2.setPath(path2);
            updatedImage2.setTimestamp(ZonedDateTime.now().toString());
            imageDao.save(updatedImage2);
        }

        if (image3 != null) {
            if (oldImages.length >= 4) {
                imageDao.delete(oldImages[3]);
            }
            String path3 = imageService.store(image3);
            Image updatedImage3 = new Image();
            updatedImage3.setProductId(product.getProductId());
            updatedImage3.setIsThumbnail(true);
            updatedImage3.setPath(path3);
            updatedImage3.setTimestamp(ZonedDateTime.now().toString());
            imageDao.save(updatedImage3);
        }
    }
}
