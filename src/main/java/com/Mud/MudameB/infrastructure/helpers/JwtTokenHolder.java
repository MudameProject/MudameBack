package com.Mud.MudameB.infrastructure.helpers;

import com.Mud.MudameB.Utils.enums.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mud.MudameB.Domain.Entity.User;

@Component
public class JwtTokenHolder {
    private static String token;

    @Autowired
    private JwtService jwtService;

    // Bloque para inicializar el token
    @PostConstruct
    public void init() {
        User user = new User();
        user.setUsername("admin");
        user.setRole(Role.ADMIN); // Suponiendo que Role es un enum con valores como ADMIN

        token = jwtService.getToken(user);
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String jwt) {
        token = jwt;
    }
}