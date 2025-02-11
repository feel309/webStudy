package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LotteryDAO;

@Service
public class LotteryService {
	
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final int LOTTO_SIZE = 6;
	
	@Autowired
    private LotteryDAO lotteryDAO;

    public LotteryService(LotteryDAO lotteryDAO) {
        this.lotteryDAO = lotteryDAO;
    }

    public List<Map<String, Object>> getBoardList(String lotteryName, Date startDate, Date endDate, int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("lotteryName", lotteryName);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("offset", (page - 1) * size);
        params.put("pageSize", size);
        return lotteryDAO.getBoardList(params);
    }

    public int getBoardCount(Map<String, Object> params) {
        return lotteryDAO.getBoardCount(params);
    }

    public void insertBoard(Map<String, Object> board) {
    	lotteryDAO.insertBoard(board);
    }
    
    public Map<String, Object> getBoardById(int id) {
        return lotteryDAO.getBoardById(id);
    }
    
    public void insertComment(int boardId, Integer parentId, String content) {
        Map<String, Object> comment = new HashMap<>();
        comment.put("boardId", boardId);
        comment.put("parentId", parentId);
        comment.put("content", content);
        lotteryDAO.insertComment(comment);
    }

    public List<Map<String, Object>> getCommentsByBoardId(int boardId) {
        return lotteryDAO.getCommentsByBoardId(boardId);
    }
    
    
    public List<Integer> generateLottoNumbers() {
        return new Random().ints(LOTTO_MIN, LOTTO_MAX + 1)
                .distinct()
                .limit(LOTTO_SIZE)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }
    
    
}