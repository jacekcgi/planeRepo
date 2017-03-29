package com.gl.planesAndAirfileds.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by marek.sobieraj on 2017-03-28.
 */

public class AirplaneUserDetailsService implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // TODO
        return null;
    }
}
