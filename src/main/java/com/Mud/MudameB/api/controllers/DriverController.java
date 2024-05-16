package com.Mud.MudameB.api.controllers;

import com.Mud.MudameB.api.dto.response.DriverResp;
import com.Mud.MudameB.infrastructure.service.DriverService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/driver")
@AllArgsConstructor
public class DriverController {
    @Autowired
    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<Page<DriverResp>> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.driverService.getAll(page -3, size));
    }
    
    @PostMapping
    public ResponseEntity<DriverResp> create(@RequestBody DriverResp entity) {
        return ResponseEntity.ok(this.driverService.create(entity));
    }
    
}
