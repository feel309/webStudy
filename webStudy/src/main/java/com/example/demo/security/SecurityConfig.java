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
        // 경로별 보안 설정
        http
            .authorizeHttpRequests((auth) -> auth
                // 루트 경로에 대한 모든 사용자의 접근 허용
                // 회원가입 페이지 및 회원가입 처리 경로 허용
                .requestMatchers("/", "/login", "/register", "/WEB-INF/**").permitAll()
                // 관리자 경로는 ADMIN 권한을 가진 사용자만 허용
                .requestMatchers("/admin").hasRole("ADMIN")
                // 그 외의 모든 경로에 대한 접근 허용
                .anyRequest().permitAll()
            );
        
        // 로그인 경로 및 로그인 요청 경로 보안 설정
        http.formLogin((auth) -> auth
            .loginPage("/login") // 커스텀 로그인 페이지 경로 설정
            .loginProcessingUrl("/loginProc") // 로그인 처리 경로 설정
            .permitAll() // /login 및 /loginProc 접근 허용
        );

        //CSRF 보호 비활성화
        http.csrf((auth)->auth
        		.disable()
        );
        
        
        return http.build();
    }
    
    //회원가입 시 비밀번호를 암호화하거나 
    //로그인 시 비밀번호를 비교하는 데 사용됨
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	
	
}
