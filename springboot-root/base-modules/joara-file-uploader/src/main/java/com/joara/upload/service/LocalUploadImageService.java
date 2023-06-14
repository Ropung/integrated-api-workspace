package com.joara.upload.service;

import com.joara.upload.properties.UploadImageProperties;
import com.joara.upload.util.DailyFileNameGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Log4j2
public final class LocalUploadImageService implements UploadImageService{
	
	private final UploadImageProperties uploadImageProperties;
	private final DailyFileNameGenerator dailyFileNameGenerator;
	
	@Override
	public String upload(MultipartFile file, String middlePath) throws IOException {
		String url = uploadImageProperties.uploadUrl();
		
		if (middlePath.startsWith("/") || middlePath.endsWith("/")) {
			int startIndex = middlePath.startsWith("/") ? 1 : 0;
			int endIndex = middlePath.endsWith("/") ? 1 : 0;
			middlePath = middlePath.substring(startIndex, middlePath.length() - endIndex);
		}
		
		if (url.endsWith("/")) {
			int endIndex = url.endsWith("/") ? 1 : 0;
			url = url.substring(0, url.length() - endIndex);
		}
		
		
		if (!file.isEmpty()) {
			url = "%s/%s%s"
					.formatted(
							url,
							middlePath,
							dailyFileNameGenerator.generateWithFullPath(file.getOriginalFilename())
					);
			
			log.info("파일 저장 fullPath={}", url);
			
			File folder = new File(url);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			
			file.transferTo(new File(url));
		}
		
		return url;
	}
}
