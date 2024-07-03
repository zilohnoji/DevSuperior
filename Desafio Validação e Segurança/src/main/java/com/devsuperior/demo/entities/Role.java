package com.devsuperior.demo.entities;

import com.devsuperior.demo.enums.RoleName;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName authority;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return authority.name();
    }
}