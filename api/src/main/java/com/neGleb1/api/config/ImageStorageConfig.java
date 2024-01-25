package com.neGleb1.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("storage")
public class ImageStorageConfig {
    
    private String location = "/app/images";
	private String host = "https://localhost/images/";

	public String getLocation() {
		return location;
	}

	public String getHost() {
		return host;
	}
}
