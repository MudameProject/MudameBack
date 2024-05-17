package com.Mud.MudameB.infrastructure.service;

import com.Mud.MudameB.Utils.enums.exceptions.BadRequestException;
import com.Mud.MudameB.Utils.messages.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.Mud.MudameB.Domain.Entity.TruckEntity;
import com.Mud.MudameB.Domain.repositories.TruckRepository;
import com.Mud.MudameB.Utils.enums.Capacity;
import com.Mud.MudameB.api.dto.request.TruckReq;
import com.Mud.MudameB.api.dto.response.DriverResp;
import com.Mud.MudameB.api.dto.response.TruckResp;
import com.Mud.MudameB.infrastructure.abstract_services.ITruckService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor

public class TruckService implements ITruckService {

  @Autowired
  private final TruckRepository truckRepository;

  @Override
  public TruckResp create(TruckReq request) {

    TruckEntity entity = this.requestToEntity(request);
      return this.entityToResponse(this.truckRepository.save(entity));}
  

  @Override
  public TruckResp get(Long id) {return this.entityToResponse(this.findById(id));}

  public TruckEntity findById(Long id) {
    return truckRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found"));
  }

  @Override
  public TruckResp update(TruckReq request, Long id) {
    TruckEntity truck = this.find(id);

    TruckEntity truckUpdate = this.requestToEntity(request);
    truckUpdate.setId(id);
    truckUpdate.setReservation(truck.getReservation());

    return this.entityToResponse(this.truckRepository.save(truckUpdate));
  }

  @Override
  public void delete(Long id) {

    this.truckRepository.delete(this.findById(id));
  }

  @Override
  public Page<TruckResp> getAll(int page, int size) {

    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }

  @Override
  public Page<TruckResp> getAll(int page, int size, Capacity CrudCapacity) {

    if (page < 0)
      page = 0;

    PageRequest pagination = null;

    switch (CrudCapacity) {
      case NONE -> pagination = PageRequest.of(page, size);
      case SMALL -> pagination = PageRequest.of(page, size, Sort.by(CAPACITY_SEARCH));
      case MEDIUM -> pagination = PageRequest.of(page, size, Sort.by(CAPACITY_SEARCH));
      case LARGE -> pagination = PageRequest.of(page, size, Sort.by(CAPACITY_SEARCH));
    }

    return this.truckRepository.findAll(pagination)
        .map(this::entityToResponse);
  }

  private TruckResp entityToResponse(TruckEntity Entity) {

    DriverResp driver = new DriverResp();
    BeanUtils.copyProperties(Entity.getDriver(), driver);

    return TruckResp.builder()
        .id(Entity.getId())
        .plate(Entity.getPlate())
        .model(Entity.getModel())
        .brand(Entity.getBrand())
        .color(Entity.getColor())
        .capacity(Entity.getCapacity())
        .build();
  }


  private TruckEntity requestToEntity(TruckReq request) {

    return TruckEntity.builder()

        .plate(request.getPlate())
        .model(request.getModel())
        .brand(request.getBrand())
        .color(request.getColor())
        .capacity(request.getCapacity())
        .build();
  }


  private TruckEntity find(Long id) {
    return this.truckRepository.findById(id)
            .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Client")));
  }

}
