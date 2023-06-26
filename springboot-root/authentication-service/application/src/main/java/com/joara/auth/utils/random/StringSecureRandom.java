package com.joara.auth.utils.random;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

@Component
@RequiredArgsConstructor
public class StringSecureRandom {
    private final SecureRandom secureRandom;
    private static final Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    public String next() {
        byte bytes[] = new byte[20];
        secureRandom.nextBytes(bytes);
        return encoder.encodeToString(bytes);
    }
}
