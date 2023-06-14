package com.joara.upload.api.dto;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

public record UploadImageDto() {
	public record UploadImageRequestDto(
			MultipartFile file
	){}
	
	@Builder
	public record UploadImageResponseDto(
			String url
	){}
}
