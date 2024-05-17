package com.Mud.MudameB.api.controllers;

import com.Mud.MudameB.api.dto.request.ReservationReq;
import com.Mud.MudameB.api.dto.response.ReservationResp;
import com.Mud.MudameB.infrastructure.abstract_services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationController {

    private final IReservationService iReservationService;

    @GetMapping
    public ResponseEntity<Page<ReservationResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.iReservationService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.iReservationService.get(id));
    }

    @PostMapping
    public ResponseEntity<ReservationResp> insert(
            @Validated @RequestBody ReservationReq request) {
        return ResponseEntity.ok(this.iReservationService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservationResp> update(
            @Validated @RequestBody ReservationReq request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.iReservationService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.iReservationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
