package com.joara.upload.api;


import com.joara.upload.properties.UploadImageProperties;
import com.joara.upload.service.UploadImageService;
import com.joara.upload.util.DailyFileNameGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/upload")
@Log4j2
public class UploadImageApi {
	
	private final UploadImageService uploadImageService;
	
	public UploadImageApi(
			UploadImageProperties uploadImageProperties,
			UploadImageService s3UploadImageService,
			UploadImageService localUploadImageService,
			DailyFileNameGenerator dailyFileNameGenerator
			) {
		this.uploadImageService = switch (uploadImageProperties.target()){
			case S3 -> s3UploadImageService;
			case LOCAL -> localUploadImageService;
			default -> throw new Error("안 만들었음.");
		};
	}
	
//	@PostMapping("/image")
//	public UploadImageResponseDto uploadImage(UploadImageRequestParams params){
//		String url =  uploadImageService.upload(params.file(), "books");
//		return new UploadImageResponseDto(url);
//	}
}
