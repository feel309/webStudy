package com.example.demo.security;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.example.demo.dao.UserDAO;
import com.example.demo.dto.UserDTO;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    
    private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);
    private final UserDAO userDAO;

    public CustomOAuth2UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    	
    	logger.info("loadUser called with userRequest: {}", userRequest);
    	
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행 중인 서비스를 구분하는 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 현재 로그인 진행 시 키가 되는 필드값 ex) 구글 : "sub", 네이버 : "id"
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        
        // 공통(naver, google)
    	String userInfoEndpointUri = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();
    	
        // 액세스 토큰 추출
        String accessToken = userRequest.getAccessToken().getTokenValue();

        // 사용자 정보 요청
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> headerResponse = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
        Map<String, Object> attributes = headerResponse.getBody();

//      Map<String, Object> attributes = oAuth2User.getAttributes(); //기존방식
        
        logger.info("User attributes: {}", attributes);
        
        String email = "";
        String name = "";
        
        //네이버 로그인
        if("naver".equals(registrationId)) {
        	
            // "response" 내부의 Map 가져오기
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");

            // Naver 로그인 사용자의 정보를 가져옴
            email = (String) response.get("email");
            name = (String) response.get("name");
        	
        }
        // 구글 로그인
        else {
        	
            // Google 로그인 사용자의 정보를 가져옴
            email = (String) attributes.get("email");
            name = (String) attributes.get("name");
        	
        }
        
        if (email == null) {
            logger.error("Email attribute is missing in user info");
            throw new OAuth2AuthenticationException("Email attribute is missing");
        }

        logger.info("User email: {}", email);

        
        // DB에서 사용자 조회
        UserDTO user = userDAO.findByUsername(name);
        
        // 사용자 정보가 없으면 저장
        if (user == null) {
            user = new UserDTO();
            user.setUsername(name);
            user.setEmail(email);
            user.setRole("ROLE_USER");
            user.setPassword("1234");
            userDAO.saveUser(user);
        }

        DefaultOAuth2User customOAuth2User = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole())),
                attributes,
                userNameAttributeName
        );

        logger.info("Custom OAuth2User created: {}", customOAuth2User);
        return customOAuth2User;
    }
}