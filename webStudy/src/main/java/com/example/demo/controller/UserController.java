package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/") 
    public String home() {
        return "home";
    }
    
    // 회원가입 페이지
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO user, Model model) {
        boolean success = userService.register(user);
        if (success) {
            return "login";
        } else {
            model.addAttribute("error", "Username already taken");
            return "register";
        }
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        UserDTO user = userService.login(username);
        // password : 입력값
        // user.getPassword() : DB값
        // 입력값 패스워드와 DB값 패스워드 일치 유무
        boolean matchYn = bCryptPasswordEncoder.matches(password, user.getPassword());
        if (user != null && matchYn != false) {
//        if (user != null && user.getPassword().equals(password)) {
            return "welcome";  // 로그인 성공 후 리다이렉트 페이지
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}
