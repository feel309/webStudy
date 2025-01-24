package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.dto.UserDTO;

public class CustomUserDetails implements UserDetails {

    private UserDTO userDTO;

    public CustomUserDetails(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
	
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	 Collection<GrantedAuthority> collection = new ArrayList<>();

         collection.add(new GrantedAuthority() {

             @Override
             public String getAuthority() {

                 return userDTO.getRole();
             }
         });
         return collection;
       }
         
    @Override
    public String getPassword() {
        // 패스워드를 반환
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        // 유저네임을 반환
        return userDTO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부
        return true;  // 기본으로 계정이 만료되지 않음
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금 여부
        return true;  // 기본으로 계정이 잠기지 않음
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명 만료 여부
        return true;  // 기본으로 자격 증명이 만료되지 않음
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성화 여부
        return true;  // 기본으로 계정이 활성화됨
    }

}
