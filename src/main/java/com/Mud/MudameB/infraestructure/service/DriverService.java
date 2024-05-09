package com.Mud.MudameB.infraestructure.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.Mud.MudameB.Utils.enums.SortType;
import com.Mud.MudameB.api.dto.request.DriverReq;
import com.Mud.MudameB.api.dto.response.DriverResp;
import com.Mud.MudameB.infraestructure.abstract_services.IDriverService;

public class DriverService implements IDriverService{

  @Override
  public DriverResp create(DriverReq request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public DriverResp get(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'get'");
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

  @Override
  public Page<DriverResp> getAll(int page, int size, SortType sortType) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }

  @Override
  public List<DriverResp> search(String name) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'search'");
  }
  
}
