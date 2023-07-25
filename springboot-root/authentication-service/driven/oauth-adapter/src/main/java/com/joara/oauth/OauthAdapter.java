package com.joara.oauth;

import com.joara.auth.clients.OauthPort;
import com.joara.auth.clients.dto.OauthClientDto.OauthRequestClientDto;
import com.joara.auth.clients.dto.OauthClientDto.OauthResponse;
import com.joara.auth.domain.model.type.CertType;
import com.joara.oauth.service.OauthServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class OauthAdapter implements OauthPort {

    private final OauthServiceFactory oauthServiceFactory;

    @Override
    public OauthResponse findOauthResponse(OauthRequestClientDto requestData, CertType certBy) {
        return oauthServiceFactory
                .create(certBy)
                .authenticate(requestData);
    }
}
