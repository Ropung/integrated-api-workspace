package com.joara.member;

import com.joara.book.exception.BookErrorCode;
import com.joara.clients.MemberQueryPort;
import com.joara.member.MemberReadModels.MemberIdReadModel;
import com.joara.member.MemberReadModels.MemberProfileReadModel;
import com.joara.util.RestWithAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class MemberQueryClientAdapter implements MemberQueryPort {
    private final RestTemplate restTemplate; // AJAX 같은 거.
    private final RestWithAccessToken restWithAccessToken;

    private static final class TempServiceDiscoveryResults {
        static final String MEMBER_QUERY_SERVICE = "http://localhost:8080";
    }

    @Override
    public Optional<MemberIdReadModel> findIdByEmail(String email) {
        String target = TempServiceDiscoveryResults.MEMBER_QUERY_SERVICE;
        String path = "/members/%s/id".formatted(email);

        ResponseEntity<MemberIdReadModel> memberIdResponse =
                restTemplate.getForEntity(
                        "%s%s".formatted(target, path), // 받아 올 URL
                        MemberIdReadModel.class // 받아 올 타입
                );

        if (!memberIdResponse.getStatusCode().is2xxSuccessful()) {
            throw new IllegalStateException("...");
        }

        return Optional.ofNullable(memberIdResponse.getBody());
    }

    @Override
    public Optional<MemberProfileReadModel> findProfileByEmail(String email, String accessToken) {
        String target = TempServiceDiscoveryResults.MEMBER_QUERY_SERVICE;
        String path = "/members/me"; // input

        // TODO 나중에 볼거임
        ResponseEntity<Optional<MemberProfileReadModel>> responseEntity =
                restWithAccessToken.getOptional(
                        "%s%s".formatted(target, path),
                        accessToken,
                        MemberProfileReadModel.class
                );

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw BookErrorCode.SERVICE_UNAVAILABLE.defaultException();
        }

        return responseEntity.getBody();
    }
}
