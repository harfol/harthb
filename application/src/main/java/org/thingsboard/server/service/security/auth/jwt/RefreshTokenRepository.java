package org.thingsboard.server.service.security.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thingsboard.server.service.security.model.SecurityUser;
import org.thingsboard.server.service.security.model.token.JwtToken;
import org.thingsboard.server.service.security.model.token.JwtTokenFactory;

@Component
public class RefreshTokenRepository {

    private final JwtTokenFactory tokenFactory;

    @Autowired
    public RefreshTokenRepository(final JwtTokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    public JwtToken requestRefreshToken(SecurityUser user) {
        return tokenFactory.createRefreshToken(user);
    }

}
