package com.example.api.config;

import com.example.api.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


/**
 * @author Max Borowski
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles(Role.ROLE_ADMIN.toString())
                .build();
        UserDetails rolePosts = User.builder()
                .username("posts")
                .password(encoder.encode("posts"))
                .roles(Role.ROLE_POSTS.toString())
                .build();
        UserDetails roleAlbums = User.builder()
                .username("albums")
                .password(encoder.encode("albums"))
                .roles(Role.ROLE_ALBUMS.toString())
                .build();
        UserDetails roleUsers = User.builder()
                .username("users")
                .password(encoder.encode("users"))
                .roles(Role.ROLE_USERS.toString())
                .build();
        return new InMemoryUserDetailsManager(admin, rolePosts, roleAlbums, roleUsers);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( auth -> auth.requestMatchers("/vktest/posts/**").hasAnyRole(Role.ROLE_ADMIN.toString(), Role.ROLE_POSTS.toString())
                        .requestMatchers("/vktest/users/**").hasAnyRole(Role.ROLE_ADMIN.toString(), Role.ROLE_USERS.toString())
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
