package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.LotteryService;

@Controller
public class LotteryController {
    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/board")
    public String board(@RequestParam(defaultValue = "1") int page,
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
    
    
    

    @PostMapping("/board/add")
    public String addBoard(@RequestParam String purchaseDate,
                           @RequestParam String lotteryName,
                           @RequestParam int drawNumber,
                           @RequestParam int quantity,
                           @RequestParam String orderNumber) {
        HashMap<String, Object> board = new HashMap<>();
        board.put("purchaseDate", purchaseDate);
        board.put("lotteryName", lotteryName);
        board.put("drawNumber", drawNumber);
        board.put("quantity", quantity);
        board.put("orderNumber", orderNumber);

        lotteryService.insertBoard(board);
        return "redirect:/board";
    }
}
