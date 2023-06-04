package com.joara.util.time;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class ServerTime {
    public static OffsetDateTime now() {
        return OffsetDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public static LocalDateTime nowLocalDateTime() {
        return now().toLocalDateTime();
    }
}
