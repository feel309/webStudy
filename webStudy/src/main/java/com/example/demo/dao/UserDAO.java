package com.example.demo.dao;

import com.example.demo.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    void saveUser(UserDTO user);
    UserDTO findByUsername(String username);
}
