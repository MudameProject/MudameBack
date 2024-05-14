package com.Mud.MudameB.infrastructure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Mud.MudameB.Domain.Entity.ReservationEntity;
import com.Mud.MudameB.Domain.Entity.ClientEntity;
import com.Mud.MudameB.Domain.repositories.ClientRepository;
import com.Mud.MudameB.Utils.enums.SortType;
import com.Mud.MudameB.Utils.messages.ErrorMessages;
import com.Mud.MudameB.api.dto.request.ClientReq;
import com.Mud.MudameB.api.dto.response.DriverResp;
import com.Mud.MudameB.api.dto.response.ReservationToClient;
import com.Mud.MudameB.api.dto.response.TruckResp;
import com.Mud.MudameB.api.dto.response.ClientResp;
import com.Mud.MudameB.infrastructure.abstract_services.IClientService;
import com.Mud.MudameB.Utils.enums.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IClientService {

    @Autowired
    private final ClientRepository UserRepository;

    @Override
    public ClientResp create(ClientReq request) {
        ClientEntity user = this.requestToEntity(request);
        user.setReservation(new ArrayList<>());
        return this.entityToResp(this.UserRepository.save(user));
    }

    @Override
    public ClientResp get(Long id) {
        return this.entityToResp(this.find(id));
    }

    @Override
    public ClientResp update(ClientReq request, Long id) {
        ClientEntity user = this.find(id);

        ClientEntity userUpdate = this.requestToEntity(request);
        userUpdate.setId(id);
        userUpdate.setReservation(user.getReservation());

        return this.entityToResp(this.UserRepository.save(userUpdate));
    }

    @Override
    public void delete(Long id) {
        ClientEntity user = this.find(id);
        this.UserRepository.delete(user);
    }

    @SuppressWarnings("null")
    @Override
    public Page<ClientResp> getAll(int page, int size, SortType sortType) {

        if (page > 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.UserRepository.findAll(pagination)
                .map(this::entityToResp);
    }

    private ClientResp entityToResp(ClientEntity entity) {

        List<ReservationToClient> reservation = entity.getReservation()
                .stream()
                .map(this::entityToResponseReservation)
                .collect(Collectors.toList());

        return ClientResp.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .zipCode(entity.getZipCode())
                .reservations(reservation)
                .build();
    }

    private ReservationToClient entityToResponseReservation(ReservationEntity entity) {

        TruckResp truck = new TruckResp();
        BeanUtils.copyProperties(entity.getTruck(), truck);

        DriverResp driver = new DriverResp();
        BeanUtils.copyProperties(entity.getDriver(), driver);

        return ReservationToClient.builder()
                .id(entity.getId())
                .dateTime(entity.getDateTime())
                .origin(entity.getOrigin())
                .destiny(entity.getDestiny())
                .truck(truck)
                .driver(driver)
                .build();
    }

    private ClientEntity requestToEntity(ClientReq request) {
        return ClientEntity.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .zipCode(request.getZipCode())
                .build();
    }

    private ClientEntity find(Long id) {
        return this.UserRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("User")));
    }

}
