package com.joara.upload.util;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Component
public final class DailyFileNameGenerator implements FileNameGenerator {
	@Override
	public String generateWithFullPath(String originFileName) {
		UUID uuid = UUID.randomUUID();
		OffsetDateTime now = OffsetDateTime.now(ZoneId.of("Asia/Seoul"));
		
		return MessageFormat.format("/{0,number,#}/{1}/{2}/{3}__{4}",
				now.getYear(),
				now.getMonth().getValue(),
				now.getDayOfMonth(),
				uuid,
				originFileName
		);
	}
}