package com.Mud.MudameB.api.controllers;

import com.Mud.MudameB.infrastructure.helpers.JwtTokenHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import com.Mud.MudameB.api.dto.request.ClientRegiserReq;
import com.Mud.MudameB.api.dto.request.LoginReq;
import com.Mud.MudameB.api.dto.request.RegisterReq;
import com.Mud.MudameB.api.dto.response.AuthResp;
import com.Mud.MudameB.infrastructure.abstract_services.IAuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final IAuthService authService;

    @PostMapping(path = "/auth/login")
    public ResponseEntity<AuthResp> login(
            @Validated @RequestBody LoginReq request) {
        return ResponseEntity.ok(this.authService.login(request));
    }

    @PostMapping(path = "/auth/register")
    public ResponseEntity<AuthResp> register(
            @Validated @RequestBody RegisterReq request) {
        return ResponseEntity.ok(this.authService.register(request));
    }

}