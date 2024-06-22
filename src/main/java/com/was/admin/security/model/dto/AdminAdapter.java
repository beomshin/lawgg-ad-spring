package com.was.admin.security.model.dto;

import com.was.admin.entities.AdminTb;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class AdminAdapter extends User {

    private AdminTb adminTb;

    public AdminAdapter(AdminTb adminTb) {
        super(adminTb.getLoginId(), adminTb.getPassword() == null ? "" : adminTb.getPassword(), authorities());
        this.adminTb = adminTb;
    }

    private static Collection<? extends GrantedAuthority> authorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
