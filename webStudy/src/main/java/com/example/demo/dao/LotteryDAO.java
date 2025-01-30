package com.example.demo.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LotteryDAO {
    List<Map<String, Object>> getBoardList(Map<String, Object> params);
    int getBoardCount(Map<String, Object> params);
    void insertBoard(Map<String, Object> board);
    
    // 댓글 기능 추가
    void insertComment(Map<String, Object> comment);
    List<Map<String, Object>> getCommentsByBoardId(int boardId);
}