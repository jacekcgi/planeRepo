package com.gl.planesAndAirfileds.security.provider;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

/**
 * Created by marek.sobieraj on 2017-03-28.
 */

public class AirplaneUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // TODO atm hardcoded by spring password encoder! rawPass = 'password'
        String hardcodedPassword = "$2a$05$E957LMo2SWU.vbpL3/AaSul4l1hD4OdyQmIXslET2.YTAxIc5.76y";
        return new User(s, hardcodedPassword, Collections.emptyList());
    }
}
