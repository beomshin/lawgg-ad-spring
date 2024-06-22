package com.was.admin.security.service;

import com.was.admin.entities.AdminTb;
import com.was.admin.repositories.AdminTbRepository;
import com.was.admin.security.model.dto.AdminAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class JwtDetailService implements UserDetailsService {

    private final AdminTbRepository adminTbRepository;

    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
        log.debug("{} =============>", adminId);
        AdminTb adminTb = adminTbRepository
                .findByAdminId(Long.parseLong(adminId))
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found in the database"));
        return new AdminAdapter(adminTb);
    }
}
