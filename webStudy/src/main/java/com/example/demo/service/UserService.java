package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public boolean register(UserDTO user) {
        // 회원가입 로직 (중복 확인)
        if (userDAO.findByUsername(user.getUsername()) != null) {
            return false;  // 이미 존재하는 사용자
        }
        userDAO.saveUser(user);
        return true;  // 성공
    }

    public UserDTO login(String username) {
        return userDAO.findByUsername(username);  // 로그인: 사용자 정보 반환
    }
}
