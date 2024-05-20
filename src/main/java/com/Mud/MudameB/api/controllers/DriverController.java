package com.Mud.MudameB.api.controllers;

import com.Mud.MudameB.api.dto.request.DriverReq;
import com.Mud.MudameB.api.dto.response.DriverResp;
import com.Mud.MudameB.infrastructure.abstract_services.IDriverService;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/driver")
@AllArgsConstructor
public class DriverController {
    private final IDriverService driverService;

    @GetMapping
    public ResponseEntity<Page<DriverResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.driverService.getAll(page - 3, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DriverResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.driverService.get(id));
    }

    @PostMapping
    public ResponseEntity<DriverResp> insert(
            @Validated @RequestBody DriverReq driver) {
        return ResponseEntity.ok(this.driverService.create(driver));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.driverService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DriverResp> update(
            @Validated @RequestBody DriverReq request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.driverService.update(request, id));
    }
}
