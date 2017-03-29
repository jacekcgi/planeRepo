package com.gl.planesAndAirfileds.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by marek.sobieraj on 2017-03-28.
 */
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        // check password here
        if (!(authentication instanceof JwtAuthenticationToken))
        {
            String rawPassword = authentication.getCredentials().toString();
            if (StringUtils.isBlank(rawPassword))
            {
                throw new BadCredentialsException("Missing password for user " + userDetails.getUsername());
            }
            String password = userDetails.getPassword();
            if (StringUtils.isBlank(password))
            {
                throw new BadCredentialsException("Missing password for user " + userDetails.getUsername());
            }
            // todo check password here
//            if (!passwordEncoder.matches(rawPassword, password))
//            {
//                throw new BadCredentialsException("Password for user " + userDetails.getUsername() + " did not match");
//            }
        }

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        // retrieve user from
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
