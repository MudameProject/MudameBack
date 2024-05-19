package com.Mud.MudameB.infrastructure.service;

import java.util.ArrayList;

import com.Mud.MudameB.Domain.Entity.DriverEntity;
import com.Mud.MudameB.Domain.repositories.DriverRepository;
import com.Mud.MudameB.api.dto.request.DriverRegisterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Mud.MudameB.Domain.Entity.ClientEntity;
import com.Mud.MudameB.Domain.Entity.User;
import com.Mud.MudameB.Domain.repositories.ClientRepository;
import com.Mud.MudameB.Domain.repositories.UserRepository;
import com.Mud.MudameB.Utils.enums.Role;
import com.Mud.MudameB.Utils.enums.exceptions.BadRequestException;
import com.Mud.MudameB.api.dto.request.ClientRegiserReq;
import com.Mud.MudameB.api.dto.request.LoginReq;
import com.Mud.MudameB.api.dto.request.RegisterReq;
import com.Mud.MudameB.api.dto.response.AuthResp;
import com.Mud.MudameB.infrastructure.abstract_services.IAuthService;
import com.Mud.MudameB.infrastructure.helpers.JwtService;

import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final DriverRepository driverRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public AuthResp login(LoginReq request) {
        try {
            // autenticar en la app
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(),
                            request.getPassword()));
        } catch (Exception e) {
            throw new BadRequestException("credenciales invalidas");
        }

        // si el usuario se autentico correctamente
        User user = this.findByUserName(request.getUserName());

        if (user == null) {
            throw new BadRequestException("el usuario no es esta registrado");
        }

        return AuthResp.builder()
                .message("atenticado correctamente")
                .token(this.jwtService.getToken(user))
                .build();
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
                .role(Role.ADMIN)
                .build();

        // 3.guardar el nuevo usuario en la DB
        this.userRepository.save(user);

        return AuthResp.builder()
                .message("se registro exitosamente")
                .token(this.jwtService.getToken(user))
                .build();
    }

    @Override
    // metodo para registrar un cliente
    public AuthResp registerClient(ClientRegiserReq request) {

        // validamo sque el usuario no exista
        User exist = this.findByUserName(request.getUserName());

        if (exist != null) {
            throw new BadRequestException("el usuario ya esta registrado");
        }

        // construimos el usuario

        User user = User.builder()
                .username(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CLIENT)
                .build();

        // ahora lo guardamos en la bse de datos
        User usersave = this.userRepository.save(user);

        // construimos el cliente

        ClientEntity client = ClientEntity.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .phoneNumber(Long.valueOf(request.getPhoneNumber()))
                .address(request.getAddress())
                .zipCode(request.getZipCode())
                .user(usersave)
                .reservation(new ArrayList<>())
                .build();

        this.clientRepository.save(client);

        return AuthResp.builder()
                .message("cliente registrado correctamente")
                .token(this.jwtService.getToken(usersave))
                .build();
    }

    private User findByUserName(String userName) {
        return this.userRepository.findByUsername(userName)
                .orElse(null);
    }

    @Override
    public AuthResp registerDriver(DriverRegisterReq request) {
        
        User exist = this.findByUserName(request.getUserName());

        if (exist != null) {
            throw new BadRequestException("El usuario ya está registrado");
        }

        User user = User.builder()
                    .username(request.getUserName())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.DRIVER)
                    .build();

        User userSave = this.userRepository.save(user);

        DriverEntity driver = DriverEntity.builder()
                    .name(request.getName())
                    .lastName(request.getLastName())
                    .phoneNumber(Long.valueOf(request.getPhoneNumber()))
                    .auxiliar(request.getAuxiliar())
                    .license(request.getLicense())
                    .licenseType(request.getLicenseType())
                    .build();

        this.driverRepository.save(driver);

        return AuthResp.builder()
                    .message("condoctor registrado correctamente")
                    .token(this.jwtService.getToken(userSave))
                    .build();
    }
    


}