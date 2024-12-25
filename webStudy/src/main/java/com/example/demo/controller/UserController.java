package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
//        return "redirect:/login";
        return "login";
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
        if (user != null && user.getPassword().equals(password)) {
            return "welcome";  // 로그인 성공 후 리다이렉트 페이지
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}
