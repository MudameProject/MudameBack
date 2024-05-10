package com.Mud.MudameB.infrastructure.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.Mud.MudameB.Domain.Entity.ReservationEntity;
import com.Mud.MudameB.Domain.Entity.UserEntity;
import com.Mud.MudameB.Domain.repositories.UserRepository;
import com.Mud.MudameB.Utils.enums.SortType;
import com.Mud.MudameB.api.dto.request.UserReq;
import com.Mud.MudameB.api.dto.response.DriverResp;
import com.Mud.MudameB.api.dto.response.ReservationResp;
import com.Mud.MudameB.api.dto.response.ReservationToUser;
import com.Mud.MudameB.api.dto.response.UserResp;
import com.Mud.MudameB.infrastructure.abstract_services.IUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRepository UserRepository;

    @Override
    public UserResp create(UserReq request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public UserResp get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public UserResp update(UserReq request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<UserResp> getAll(int page, int size, SortType sortType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    private UserResp entityToResp(UserEntity entity) {

        List<ReservationToUser> reservation = entity.getReservation()
                .stream()
                .map(this::entityToResponseReservation)
                .collect(Collectors.toList());

        return UserResp.builder()
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

    private ReservationToUser entityToResponseReservation(ReservationEntity entity) {

        ReservationResp reservation = new ReservationResp();
        BeanUtils.copyProperties(entity.getReservation(), reservation);

        DriverResp driver = new DriverResp();
        BeanUtils.copyProperties(entity.getDriver(), driver);
    }

}
