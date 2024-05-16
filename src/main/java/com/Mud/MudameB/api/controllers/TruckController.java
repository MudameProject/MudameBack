package com.Mud.MudameB.api.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.Mud.MudameB.Utils.enums.Capacity;
import com.Mud.MudameB.api.dto.response.TruckResp;
import com.Mud.MudameB.infrastructure.abstract_services.ITruckService;


import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/truck")
@AllArgsConstructor
public class TruckController {
    
    @Autowired
    private final ITruckService truckService;

    @GetMapping
    public ResponseEntity<Page<TruckResp>> getAll(@Validated 
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "10") int size,
    
    @RequestHeader(required = false) Capacity CAPACITY_SEARCH) {
        
        if (Objects.isNull(CAPACITY_SEARCH))
            CAPACITY_SEARCH = Capacity.NONE;

        return ResponseEntity.ok(this.truckService.getAll(page - 1 , size, CAPACITY_SEARCH));
    };

}
