package com.joara.book.properties.upload.type;

public enum ImageStorageTargetType {
	// SAN마다 자바 라이브러리 제공 여부에 따라서 이렇게 더 구체적으로 구분하는 게 서비스단 구분해 놓기 편할 수 있음.
	S3, // One of SAN
	LOCAL,
	NAS, // example
	DAS // -> Similar to "LOCAL"
}
