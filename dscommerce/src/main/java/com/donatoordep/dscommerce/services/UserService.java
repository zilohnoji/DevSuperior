package com.donatoordep.dscommerce.services;

import com.donatoordep.dscommerce.dto.UserDTO;
import com.donatoordep.dscommerce.entities.Role;
import com.donatoordep.dscommerce.entities.User;
import com.donatoordep.dscommerce.projections.UserDetailsProjection;
import com.donatoordep.dscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = new User();

        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());
        result.forEach(obj -> user.addRole(new Role(obj.getRoleId(), obj.getAuthority())));

        return user;
    }

    protected User authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            return repository.findByEmail(username).get();
        } catch (Exception exception) {
            throw new UsernameNotFoundException(exception.getMessage());
        }
    }

    public UserDTO me(){
        return new UserDTO(authenticated());
    }
}
