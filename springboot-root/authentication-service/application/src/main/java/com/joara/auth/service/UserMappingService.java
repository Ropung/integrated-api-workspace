package com.joara.auth.service;

import com.joara.auth.domain.model.Member;
import com.joara.auth.repository.MemberCommandRepository;
import com.joara.auth.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class UserMappingService implements UserDetailsService {

    private final MemberQueryRepository memberQueryRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> optionalAuthentication = memberQueryRepository.findByEmail(email);
        if (optionalAuthentication.isEmpty()) new IllegalArgumentException("...");
        
        Member authInfo = optionalAuthentication.get();

        return User
                .withUsername(email)
                .password(authInfo.password)
                .authorities("USER") // ...
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}