package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.UserDTO;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean register(UserDTO user) {
    	
        try {
            // 비밀번호 암호화
        	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            
            // 회원가입 성공 시 기본 역할 설정
        	user.setRole("ROLE_USER"); // MyBatis에 맞춰 그대로 유지

            // 회원가입 로직 (중복 확인)
            if (userDAO.findByUsername(user.getUsername()) != null) {
                return false;  // 이미 존재하는 사용자
            }
            // MyBatis DAO로 전달
        	userDAO.saveUser(user); // MyBatis DAO 메서드 호출
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return true;  // 성공
    }

    public UserDTO login(String username) {
        return userDAO.findByUsername(username);  // 로그인: 사용자 정보 반환
    }
}
