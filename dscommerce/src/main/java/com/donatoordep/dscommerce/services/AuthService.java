package com.donatoordep.dscommerce.services;

import com.donatoordep.dscommerce.entities.User;
import com.donatoordep.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(Long id) {
        User me = userService.authenticated();
        if(!me.hasRole("ROLE_ADMIN") && !me.getId().equals(id)){
            throw new ForbiddenException();
        }
    }
}
