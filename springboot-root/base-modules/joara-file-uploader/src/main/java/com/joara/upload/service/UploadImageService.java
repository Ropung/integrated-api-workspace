package com.joara.upload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadImageService {
	/**
	 *
	 * @param file
	 * @return url
	 */
	String upload(MultipartFile file, String middlePath) throws IOException;
}
