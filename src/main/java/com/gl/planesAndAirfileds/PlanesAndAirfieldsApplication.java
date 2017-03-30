package com.gl.planesAndAirfileds;

import com.gl.planesAndAirfileds.domain.api.Mappings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class PlanesAndAirfieldsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanesAndAirfieldsApplication.class, args);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, Mappings.ERROR_401);
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, Mappings.ERROR_403);
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, Mappings.ERROR_404);
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, Mappings.ERROR_500);
            container.addErrorPages(error401Page, error403Page, error404Page, error500Page);
        });
    }
}
