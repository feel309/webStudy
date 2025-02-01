package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/register", "/WEB-INF/**").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin((auth) -> auth
                .loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/lotto/board")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
            )
            .oauth2Login((auth) -> auth
                .loginPage("/login")  // OAuth2 로그인도 같은 로그인 페이지 사용
                .defaultSuccessUrl("/lotto/board") // 로그인 성공 후 이동할 페이지
            )
            .sessionManagement((auth) -> auth
                .sessionFixation((sessionFixation) -> sessionFixation.newSession())
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/login")
            )
            .logout((auth) -> auth
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
            )
            .csrf((auth) -> auth.disable());

        return http.build();
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
