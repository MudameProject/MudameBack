package com.Mud.MudameB.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Mud.MudameB.Domain.Entity.User;
import com.Mud.MudameB.Domain.repositories.UserRepository;
import com.Mud.MudameB.Utils.enums.Role;
import com.Mud.MudameB.Utils.enums.exceptions.BadRequestException;
import com.Mud.MudameB.api.dto.request.LoginReq;
import com.Mud.MudameB.api.dto.request.RegisterReq;
import com.Mud.MudameB.api.dto.response.AuthResp;
import com.Mud.MudameB.infrastructure.abstract_services.IAuthService;
import com.Mud.MudameB.infrastructure.helpers.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResp login(LoginReq request) {
        return null;
    }

    @Override
    public AuthResp register(RegisterReq request) {
        // 1.validar que el username no exista
        User exist = this.findByUserName(request.getUserName());

        if (exist != null) {
            throw new BadRequestException("este usuario ya esta registrado");
        }

        // 2.construimos el nuevo usuario
        User user = User.builder()
                .username(request.getUserName())
                // guardar la contraseña codificada
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CLIENT)
                .build();

        // 3.guardar el nuevo usuario en la DB
        this.userRepository.save(user);

        return AuthResp.builder()
                .message("se registro exitosamente")
                .token(this.jwtService.getToken(user))
                .build();
    }

    private User findByUserName(String userName) {
        return this.userRepository.findByUsername(userName)
                .orElse(null);
    }
}