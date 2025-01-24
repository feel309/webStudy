package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.UserDTO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//사용자가 입력한 아이디를을 통해 DB에서 사용자 정보를 조회하고, 이를 UserDTO 객체에 저장.
		UserDTO userDTO = userDAO.findByUsername(username);
		
        if (userDTO != null) {
            return new CustomUserDetails(userDTO);
        }

        return null;
	}
	
}
