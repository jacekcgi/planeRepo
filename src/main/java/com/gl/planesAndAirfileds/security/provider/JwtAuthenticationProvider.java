package com.gl.planesAndAirfileds.security.provider;

import com.gl.planesAndAirfileds.security.JwtAuthenticationToken;
import com.gl.planesAndAirfileds.security.TokenAuthenticationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by marek.sobieraj on 2017-03-28.
 */
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private PasswordEncoder passwordEncoder;

    private UserDetailsService userDetailsService;

    public JwtAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        // check password here
        if (!(authentication instanceof JwtAuthenticationToken)) {
            String rawPassword = authentication.getCredentials().toString();
            if (StringUtils.isBlank(rawPassword)) {
                throw new BadCredentialsException("Missing password for user " + userDetails.getUsername());
            }
            String password = userDetails.getPassword();
            if (StringUtils.isBlank(password)) {
                throw new BadCredentialsException("Missing password for user " + userDetails.getUsername());
            }
            if (!passwordEncoder.matches(rawPassword, password)) {
                throw new BadCredentialsException("Password for user " + userDetails.getUsername() + " did not match");
            }
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        UserDetails loadedUser;
        try {
            loadedUser = this.userDetailsService.loadUserByUsername(username);
        }
        catch (UsernameNotFoundException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

        if (loadedUser == null) {
            throw new UsernameNotFoundException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
                                                         UserDetails user) {
        JwtAuthenticationToken result;
        if (!(authentication instanceof JwtAuthenticationToken)) {

            String token = TokenAuthenticationService.createJwtToken(authentication.getName());
            result = new JwtAuthenticationToken(
                    principal,
                    token,
                    5000,
                    null);
        }
        else {
            JwtAuthenticationToken jwtAuthentication = (JwtAuthenticationToken) authentication;
            result = new JwtAuthenticationToken(
                    principal,
                    jwtAuthentication.getToken(),
                    jwtAuthentication.getTokenExpiryTimeInSeconds(), null);
        }

        result.setDetails(authentication.getDetails());
        return result;
    }
}
