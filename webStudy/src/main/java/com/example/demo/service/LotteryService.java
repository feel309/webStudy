package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LotteryDAO;

@Service
public class LotteryService {
	
	@Autowired
    private LotteryDAO LotteryDAO;

    public LotteryService(LotteryDAO lotteryMapper) {
        this.LotteryDAO = lotteryMapper;
    }

    public List<Map<String, Object>> getBoardList(String lotteryName, Date startDate, Date endDate, int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("lotteryName", lotteryName);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("offset", (page - 1) * size);
        params.put("pageSize", size);
        return LotteryDAO.getBoardList(params);
    }

    public int getBoardCount(Map<String, Object> params) {
        return LotteryDAO.getBoardCount(params);
    }

    public void insertBoard(Map<String, Object> board) {
    	LotteryDAO.insertBoard(board);
    }
}