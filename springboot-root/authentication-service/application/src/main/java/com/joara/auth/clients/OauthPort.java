package com.joara.auth.clients;

import com.joara.auth.clients.dto.OauthClientDto.OauthRequestClientDto;
import com.joara.auth.clients.dto.OauthClientDto.OauthResponse;
import com.joara.auth.domain.model.type.CertType;

public interface OauthPort {
    OauthResponse findOauthResponse(OauthRequestClientDto requestData, CertType certBy);
}
