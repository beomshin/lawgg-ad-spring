package com.was.admin.security.service;

import com.was.admin.entities.AdminTb;
import com.was.admin.entities.UserTb;
import com.was.admin.repositories.AdminTbRepository;
import com.was.admin.repositories.UserTbRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserDetailService implements  UserDetailsService {

    private final UserTbRepository userTbRepository;
    private final AdminTbRepository adminTbRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<AdminTb> adminTb = adminTbRepository.findByLoginId(loginId);
        if (!adminTb.isPresent()) {
            throw new UsernameNotFoundException("로그인 아이디가 없습니다.");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(String.valueOf(adminTb.get().getAdminId()), adminTb.get().getPassword(), authorities);
    }



}
