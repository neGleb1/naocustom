package com.neGleb1.api.exception;

public class ImageNotFoundException extends StorageException {

	public ImageNotFoundException(String message) {
		super(message);
	}

	public ImageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
