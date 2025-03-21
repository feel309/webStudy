package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    
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
                .defaultSuccessUrl("/lotto/board", true) // 로그인 성공 후 이동할 페이지
                .failureUrl("/login?error=true") // 로그인 실패 시 에러 메시지 추가
                .userInfoEndpoint(userInfoEndpointConfig -> // 로그인 성공 후 사용자 정보를 가져올 때의 설정
                userInfoEndpointConfig.userService(customOAuth2UserService)) // 로그인 성공 후 후속 조치
            )
            .logout((auth) -> auth //로그아웃 설정
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout") // 로그아웃 시 명확한 URL 제공
                .invalidateHttpSession(true)
                .clearAuthentication(true)  // 인증 정보까지 삭제
                .deleteCookies("JSESSIONID") // 세션 쿠키 삭제
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
