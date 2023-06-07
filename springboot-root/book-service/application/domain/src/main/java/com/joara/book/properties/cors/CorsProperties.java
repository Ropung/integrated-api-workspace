package com.joara.book.properties.cors;


import com.joara.book.properties.cors.allowed.CorsAllowedProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "app.security.cors")
@ConfigurationPropertiesBinding
@ConfigurationPropertiesScan
public record CorsProperties(
		@NestedConfigurationProperty CorsAllowedProperties allowed,
		String[] exposedHeaders
) {
	public CorsProperties {
		System.out.println("exposedHeaders: " + exposedHeaders.length);
		
		if (exposedHeaders == null || exposedHeaders.length == 0) {
			exposedHeaders = new String[] {"*"};
		}
	}
}
