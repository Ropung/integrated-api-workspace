package com.joara.member;

import com.joara.clients.MemberQueryPort;
import com.joara.member.MemberReadModels.MemberIdReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class MemberQueryAdapter implements MemberQueryPort {
    private final RestTemplate restTemplate;

    private static final class TempServiceDiscoveryResults {
        static final String MEMBER_QUERY_SERVICE = "http://localhost:8080";
    }

    @Override
    public Optional<MemberIdReadModel> findIdByEmail(String email) {
        String target = TempServiceDiscoveryResults.MEMBER_QUERY_SERVICE;
        String path = "/members/%s/id".formatted(email);

        ResponseEntity<MemberIdReadModel> memberIdResponse =
                restTemplate.getForEntity(
                        "%s%s".formatted(target, path),
                        MemberIdReadModel.class
                );

        if (!memberIdResponse.getStatusCode().is2xxSuccessful()) {
            throw new IllegalStateException("...");
        }

        return Optional.ofNullable(memberIdResponse.getBody());
    }
}
