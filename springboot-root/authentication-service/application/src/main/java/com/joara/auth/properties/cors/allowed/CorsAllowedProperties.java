package com.joara.auth.properties.cors.allowed;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationPropertiesBinding
@Log4j2
public record CorsAllowedProperties(
		String[] headers,
		String[] methods,
		String[] origins,
		Boolean credentials
) {
	public CorsAllowedProperties {
		StringBuilder logContent = new StringBuilder();
		
		log.debug("headers.length=" + headers.length);
		
		logContent.append("Allowed Headers: \n");
		for (var header: headers) {
			logContent.append("- ");
			logContent.append(header);
			logContent.append('\n');
		}
		
		logContent.append("Allowed Methods: \n");
		for (var item: methods) {
			logContent.append("- ");
			logContent.append(item);
			logContent.append('\n');
		}
		
		logContent.append("Allowed Origins: \n");
		for (var item: origins) {
			logContent.append("- ");
			logContent.append(item);
			logContent.append('\n');
		}
		
		log.info("CorsProperties Allowed Contents: \n{}",
				logContent.toString());
		
		if (headers == null || headers.length == 0) {
			headers = new String[] {"*"};
		}
		if (methods == null || methods.length == 0) {
			methods = new String[] {"*"};
		}
			if (origins == null) {
				origins = new String[] {};
		}
		if (credentials == null) credentials = false;
	}
}
