package com.example.demo.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
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
    public String home(Model model, Principal principal) {
        // OAuth2 로그인 사용자의 ID 가져오기
        String id = (principal != null) ? "LoginUser" : "anonymousUser";
        model.addAttribute("id", id);

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
		
	    // OAuth2 로그인 사용자 세션 제거
	    req.getSession().invalidate();  // 현재 세션 무효화
	    return "redirect:/login?logout";  // 명확한 로그아웃 메시지 제공
	}
    
	//게시판 페이지
	@GetMapping("/lotto/board")
    public String boardPage(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String lotteryName,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            Model model) {
        // 필터 조건을 반영한 전체 게시글 수 조회
        Map<String, Object> params = new HashMap<>();
        params.put("lotteryName", lotteryName);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        int total = lotteryService.getBoardCount(params);

        // 게시글 목록 조회
        List<Map<String, Object>> boardList = lotteryService.getBoardList(lotteryName, startDate, endDate, page, size);

        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int) Math.ceil((double) total / size));
		
		return "board";
	}
	
    // 게시글 등록 페이지
    @GetMapping("/board/create")
    public String boardCreatePage() {
        return "boardCreate";
    }
	
    //관리자 페이지
    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
    
    
}
