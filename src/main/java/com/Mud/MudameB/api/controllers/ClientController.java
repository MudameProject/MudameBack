package com.Mud.MudameB.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Mud.MudameB.api.dto.request.ClientReq;
import com.Mud.MudameB.api.dto.response.ClientResp;
import com.Mud.MudameB.infrastructure.abstract_services.IClientService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/clients")
@AllArgsConstructor
public class ClientController {
    private final IClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.clientService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.clientService.get(id));
    }

    @PostMapping
    public ResponseEntity<ClientResp> insert(
            @Validated @RequestBody ClientReq request) {
        return ResponseEntity.ok(this.clientService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClientResp> update(
            @Validated @RequestBody ClientReq request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.clientService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
