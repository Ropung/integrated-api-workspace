package com.joara.upload.service;

import org.springframework.web.multipart.MultipartFile;

public final class S3UploadImageService implements UploadImageService {
	
	@Override
	public String upload(MultipartFile file, String middlePath) {
		//TODO S3적용시 참고
		throw new Error("S3UploadImageService: 추후에 필요하면 넣을 예정");
	}
}
