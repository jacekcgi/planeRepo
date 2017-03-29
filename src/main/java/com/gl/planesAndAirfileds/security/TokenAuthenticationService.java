package com.gl.planesAndAirfileds.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by marek.sobieraj on 2017-03-29.
 */
public class TokenAuthenticationService {

    // in milis
    private static final long EXPIRATIONTIME = 864_000_000; // 10 days

    private static final String SECRET = "ThisIsASecret";

    private static final String TOKEN_PREFIX = "Bearer";

    protected static final WebAuthenticationDetailsSource authenticationDetailsSource = new WebAuthenticationDetailsSource();

    public static String createJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static Authentication parseJwtToken(HttpServletRequest request, String token)
            throws AuthenticationException {
        // parse the token.
        String user = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        if (user == null) {
            InsufficientAuthenticationException e = new InsufficientAuthenticationException("Insufficient token");
//            LOGGER.debug("token verification failed", e);
            throw e;
        }
        else {
            JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(user, token);
            jwtAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
            return jwtAuthenticationToken;
        }
    }
}
