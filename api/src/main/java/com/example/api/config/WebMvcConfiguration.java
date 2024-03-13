package com.example.api.config;

import com.example.api.role.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


/**
 * @author Max Borowski
 */

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private Clock clock;

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(100000);
        filter.setIncludeHeaders(false);

        LocalDate date = LocalDate.now(clock);
        LocalDateTime time = LocalDateTime.now(clock);
        OffsetDateTime offsetTime = OffsetDateTime.now(clock);

        String currentRole = "UnAuthorized user";

        filter.setAfterMessagePrefix(offsetTime.toString() + " " + currentRole + " " + "REQUEST: ");
        return filter;
    }


}