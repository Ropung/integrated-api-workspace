package com.joara.upload.config;

import com.joara.upload.properties.UploadImageProperties;
import com.joara.upload.service.LocalUploadImageService;
import com.joara.upload.service.UploadImageService;
import com.joara.upload.util.DailyFileNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadConfig {
	@Bean
	UploadImageService uploadImageService(
			UploadImageProperties uploadImageProperties,
			DailyFileNameGenerator dailyFileNameGenerator) {
		return switch (uploadImageProperties.target()){
			case LOCAL -> new LocalUploadImageService(uploadImageProperties, dailyFileNameGenerator);
			default -> throw new Error("");
		};
	}
}
