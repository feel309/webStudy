package com.example.demo.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.LotteryService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
	@Autowired
    private LotteryService lotteryService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //초기 페이지
    @GetMapping("/") 
    public String home(Model model) {
    	
    	
    	// 시큐리티 컨텍스트에서 현재 인증된 사용자 ID(유저네임) 가져오기
    	String id = SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	// 뷰에 전달할 사용자 ID를 "id" 속성에 추가
    	model.addAttribute("id", id);
    	
    	// 시큐리티 컨텍스트에서 현재 인증된 사용자 권한(roles) 가져오기
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    	
    	// 인증된 사용자 권한 중 첫 번째 권한 가져오기 (여러 권한이 있을 경우 첫 번째 권한 사용)
    	Iterator<? extends GrantedAuthority> iter = authorities.iterator();
    	GrantedAuthority auth = iter.next();
    	String role = auth.getAuthority();

    	// 뷰에 전달할 사용자 권한(roles)을 "role" 속성에 추가
    	model.addAttribute("role", role);
    	
//    	System.out.println("ID: " + id);
//    	System.out.println("Role: " + role);
    	
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
    @PostMapping("/loginProc")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        UserDTO user = userService.login(username);
        // password : 입력값
        // user.getPassword() : DB값
        // 입력값 패스워드와 DB값 패스워드 일치 유무
        boolean matchYn = bCryptPasswordEncoder.matches(password, user.getPassword());
        if (user != null && matchYn != false) {
            return "board";  // 로그인 성공 후 리다이렉트 페이지
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
    
    
	@GetMapping("/logout")
	public String logout(HttpServletRequest req,HttpServletResponse res) throws Exception{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication !=null) {
			new SecurityContextLogoutHandler().logout(req,res,authentication);
		}
		return "redirect:/";
	}
    
	//게시판 페이지
	@GetMapping("/lotto/board")
    public String boardPage(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String lotteryName,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            Model model) {
		int total = lotteryService.getBoardCount();
		List<Map<String, Object>> boardList = lotteryService.getBoardList(lotteryName, startDate, endDate, page, size);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", (int) Math.ceil((double) total / size));
		
		return "board";
	}
	
    //관리자 페이지
    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
    
    
}
