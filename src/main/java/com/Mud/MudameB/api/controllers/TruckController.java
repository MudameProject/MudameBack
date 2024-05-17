package com.Mud.MudameB.api.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.Mud.MudameB.Utils.enums.Capacity;
import com.Mud.MudameB.api.dto.request.TruckReq;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<TruckResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.truckService.get(id));
    }

    @PostMapping
    public ResponseEntity<TruckResp> insert(
            @Validated @RequestBody TruckReq request) {
        return ResponseEntity.ok(this.truckService.create(request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.truckService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TruckResp> update(
            @Validated @RequestBody TruckReq request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.truckService.update(request, id));
    }

}
