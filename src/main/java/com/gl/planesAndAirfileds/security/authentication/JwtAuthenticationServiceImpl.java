package com.gl.planesAndAirfileds.security.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by marek.sobieraj on 2017-03-29.
 */
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationServiceImpl.class);

    // in milis
    private long expirationTokenTime;

    private String secret;

    private String tokenPrefix;

    private final WebAuthenticationDetailsSource authenticationDetailsSource = new WebAuthenticationDetailsSource();

    @Override
    public String createJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTokenTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public Authentication parseJwtToken(HttpServletRequest request, String token) throws AuthenticationException {
        // parse the token.
        String user = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace(tokenPrefix, ""))
                .getBody()
                .getSubject();
        if (user == null) {
            InsufficientAuthenticationException e = new InsufficientAuthenticationException("Insufficient token");
            LOGGER.debug("token verification failed", e);
            throw e;
        }
        else {
            JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(user, token);
            jwtAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
            return jwtAuthenticationToken;
        }
    }

    @Override
    public long getExpirationTokenTime() {
        return expirationTokenTime;
    }

    public void setExpirationTokenTime(long expirationTokenTime) {
        this.expirationTokenTime = expirationTokenTime;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasLength(tokenPrefix, "tokenPrefix property is not set");
        Assert.hasLength(secret, "secret property is not set");
        Assert.isTrue(expirationTokenTime > 0, "expirationTokenTime cannot be less or equal 0");
    }
}