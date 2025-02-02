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
                // 루트 경로에 대한 모든 사용자의 접근 허용
                // 회원가입 페이지 및 회원가입 처리 경로 허용
                .requestMatchers("/", "/login", "/register", "/WEB-INF/**").permitAll()
                // 관리자 경로는 ADMIN 권한을 가진 사용자만 허용
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin((auth) -> auth
                .loginPage("/login") // 커스텀 로그인 페이지 경로 설정
                .loginProcessingUrl("/loginProc") // 로그인 처리 경로 설정
                .defaultSuccessUrl("/lotto/board") // 로그인 성공시 이동할 경로설정
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
            )
            .oauth2Login((auth) -> auth
                .loginPage("/login")  // OAuth2 로그인도 같은 로그인 페이지 사용
                .defaultSuccessUrl("/lotto/board") // 로그인 성공 후 이동할 페이지
            )
            .sessionManagement((auth) -> auth
                .sessionFixation((sessionFixation) -> sessionFixation.newSession()) //로그인 시 세션 새로 생성
                .maximumSessions(1)				//다른 곳에서 로그인하면 이전 세션 종료됨
                .maxSessionsPreventsLogin(true)	//새로운 로그인 시도 차단됨
                .expiredUrl("/login")	//세션 만료되었을 때 사용자가 리다이렉트될 URL 설정
            )
            .logout((auth) -> auth //로그아웃 설정
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
            )
            .csrf((auth) -> auth.disable()); //CSRF 보호 비활성화

        return http.build();
    }

    //회원가입 시 비밀번호를 암호화하거나 
    //로그인 시 비밀번호를 비교하는 데 사용됨
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
