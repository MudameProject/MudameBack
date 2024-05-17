package com.Mud.MudameB.infrastructure.service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
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
    return null; //hasta aqui quede
  }

  public DriverEntity findById(Long id) {
    return driverRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver not found"));
  }

  @Override
  public DriverResp get(Long id) {
    return this.entityToResponse(this.find(id));
  }

  @Override
  public DriverResp update(DriverReq request, Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @SuppressWarnings("null")
  @Override
  public Page<DriverResp> getAll(int page, int size) {
      if (page < 0) page = 0;
      
      PageRequest pagination = null;

      

      this.driverRepository.findAll(pagination);

      return this.driverRepository.findAll(pagination)
              .map(vacant -> this.entityToResponse(vacant));
  
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
    BeanUtils.copyProperties(entity.getClient(), client);

    return DriverResp.builder()
        .id(entity.getId())
        .license(entity.getLicense())
        .licenseType(entity.getLicenseType())
        .auxiliar(entity.getAuxiliar())
        .clientID(client)
        .build();
  }


//   private DriverEntity requestToVacant(DriverReq request, DriverEntity entity) {
    
//     entity.setLicense(request.getLicense());
//     entity.setLicenseType(LicenseType.B2);
//     entity.setAuxiliar(Auxiliar.YES);
//     entity.setUserID(request.getUserID());

//     return entity;

// }
}
