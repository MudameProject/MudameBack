package com.Mud.MudameB.infrastructure.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Mud.MudameB.Domain.Entity.DriverEntity;
import com.Mud.MudameB.Domain.repositories.DriverRepository;

import com.Mud.MudameB.Utils.exceptions.BadRequestException;
import com.Mud.MudameB.Utils.messages.ErrorMessages;
import com.Mud.MudameB.api.dto.request.DriverReq;
import com.Mud.MudameB.api.dto.response.DriverResp;
import com.Mud.MudameB.api.dto.response.ClientResp;
import com.Mud.MudameB.infrastructure.abstract_services.IDriverService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class DriverService implements IDriverService {

  @Autowired
  private final DriverRepository driverRepository;

  @Override
  public DriverResp create(DriverReq request) {
    DriverEntity entity = this.requestToEntity(request);
    entity.setTrucks(new ArrayList<>());
    return this.entityToResponse(this.driverRepository.save(entity));
  }

  public DriverEntity findById(Long id) {
    return this.driverRepository.findById(id)
    .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Driver not found")));
  }

  @Override
  public DriverResp get(Long id) {
    return this.entityToResponse(this.find(id));
  }

  @Override
  public DriverResp update(DriverReq request, Long id) {
    DriverEntity driver = this.find(id);

    DriverEntity driverUpdate = this.requestToEntity(request);
    driverUpdate.setId(id);
    driverUpdate.setTrucks(driver.getTrucks());

    return this.entityToResponse(this.driverRepository.save(driverUpdate));
  }

  @Override
  public void delete(Long id) {
    DriverEntity driver = this.find(id);
    this.driverRepository.delete(driver);
  }

  @Override
  public Page<DriverResp> getAll(int page, int size) {
    if (page < 0)
      page = 0;

    PageRequest pagination = PageRequest.of(page, size);
    return this.driverRepository.findAll(pagination).map(this::entityToResponse);
  }

  @Override
  public List<DriverResp> search(String name) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'search'");
  }

  private DriverEntity find(Long id) {
    return this.driverRepository.findById(id)
        .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Conductor")));
  }

  private DriverResp entityToResponse(DriverEntity entity) {
    ClientResp client = new ClientResp();
    if (entity.getClient() != null) {
      BeanUtils.copyProperties(entity.getClient(), client);
    }

    return DriverResp.builder()
        .id(entity.getId())
        .name(entity.getName())
        .lastName(entity.getLastName())
        .phoneNumber(entity.getPhoneNumber())
        .license(entity.getLicense())
        .licenseType(entity.getLicenseType())
        .auxiliar(entity.getAuxiliar())
        .build();
  }

  private DriverEntity requestToEntity(DriverReq driver) {
    if (driver == null) {
      throw new IllegalArgumentException("El request no puede ser nulo");
    }

    return DriverEntity.builder()
        .name(driver.getName())
        .lastName(driver.getLastName())
        .phoneNumber(driver.getPhoneNumber())
        .auxiliar(driver.getAuxiliar())
        .license(driver.getLicense())
        .licenseType(driver.getLicenseType())
        .build();
  }
}
