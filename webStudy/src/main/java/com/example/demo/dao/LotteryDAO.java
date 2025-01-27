package com.example.demo.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LotteryDAO {
    List<Map<String, Object>> getBoardList(Map<String, Object> params);
    int getBoardCount();
    void insertBoard(Map<String, Object> board);
}