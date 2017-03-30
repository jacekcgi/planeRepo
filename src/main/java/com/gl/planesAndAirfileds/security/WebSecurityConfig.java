package com.gl.planesAndAirfileds.security;

import com.gl.planesAndAirfileds.security.authentication.JwtAuthenticationFilter;
import com.gl.planesAndAirfileds.security.authentication.JwtAuthenticationService;
import com.gl.planesAndAirfileds.security.authentication.JwtAuthenticationServiceImpl;
import com.gl.planesAndAirfileds.security.provider.AirplaneUserDetailsService;
import com.gl.planesAndAirfileds.security.provider.JwtAuthenticationProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by marek.sobieraj on 2017-03-28.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PATH = "/login";

    private static final String[] PUBLIC_ANT_PATHS = new String[]{
            "/", // main page
            LOGIN_PATH, // login page
            "/built/**",
            "/css/**",
            "/public/**",
            "/error401",
            "/error403",
            "/error404",
            "/error500",
    };

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(5);
    }

    @Bean
    public UserDetailsService airplaneUserDetailsService() {
        return new AirplaneUserDetailsService();
    }

    @Bean
    public AuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(passwordEncoder(), airplaneUserDetailsService(),
                jwtAuthenticationService());
    }

    @Bean
    @ConfigurationProperties("airplane.security")
    public JwtAuthenticationService jwtAuthenticationService() {
        return new JwtAuthenticationServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(PUBLIC_ANT_PATHS).permitAll()
                .anyRequest().authenticated()
                .and()
                // filter for another requests to check that user is authentificated by jwt in header
                .addFilterBefore(new JwtAuthenticationFilter(authenticationManager(), jwtAuthenticationService()),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider());
    }
}
