package com.joara.oauth.service;

import com.joara.auth.clients.dto.OauthClientDto.OauthRequestClientDto;
import com.joara.auth.clients.dto.OauthClientDto.OauthResponse;

public interface OauthService {
    OauthResponse authenticate(OauthRequestClientDto requestData);
}
