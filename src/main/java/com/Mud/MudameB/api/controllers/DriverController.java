package com.Mud.MudameB.api.controllers;

import com.Mud.MudameB.infrastructure.service;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class DriverController {
    @Autowired
    private final DriverService driverService;
}
