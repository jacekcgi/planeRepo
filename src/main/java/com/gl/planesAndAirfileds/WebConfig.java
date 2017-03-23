package com.gl.planesAndAirfileds;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * Created by marek.sobieraj on 2017-03-07.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${default.locale}")
    String defaultLocale;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("built/**").addResourceLocations("classpath:static/built/");
        registry.addResourceHandler("css/**").addResourceLocations("classpath:static/css/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    @ConfigurationProperties(prefix = "locale.change.interceptor")
    public LocaleChangeInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }

    @Bean(name = "localeResolver")
    @ConfigurationProperties(prefix = "cookie.locale.resolver")
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        Locale defaultLocale = new Locale(this.defaultLocale);
        localeResolver.setDefaultLocale(defaultLocale);
        return localeResolver;
    }

    @Bean
    @ConfigurationProperties(prefix = "message.source")
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("messages/messages", "messages/errorMessages");  // name of the resource bundle
        return source;
    }
}
