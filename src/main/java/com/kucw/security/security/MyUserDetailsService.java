package com.kucw.security.security;

import com.kucw.security.dao.MemberDao;
import com.kucw.security.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 從資料庫中查詢 Member 數據
        Member member = memberDao.getMemberByEmail(username);

        if (member == null) {
            throw new UsernameNotFoundException("Member not found for: " + username);

        } else {

            String memberEmail = member.getEmail();
            String memberPassword = member.getPassword();

            // 權限部分，先不用管
            List<GrantedAuthority> authorities = new ArrayList<>();

            // 轉換成 Spring Security 指定的 User 格式
            return new User(memberEmail, memberPassword, authorities);
        }
    }
}
