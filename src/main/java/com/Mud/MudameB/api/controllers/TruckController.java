package com.Mud.MudameB.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.Mud.MudameB.api.dto.response.TruckResp;
import com.Mud.MudameB.infrastructure.service.TruckService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/truck")
@AllArgsConstructor
public class TruckController {
    
    @Autowired
    private final TruckService truckService;

    @GetMapping
    public ResponseEntity<Page<TruckResp>> getAll(@Validated @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(this.truckService.getAll(page, size));
    };


    

}
