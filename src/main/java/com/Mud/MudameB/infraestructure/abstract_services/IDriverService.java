package com.Mud.MudameB.infraestructure.abstract_services;

import java.util.List;

import com.Mud.MudameB.api.dto.request.DriverReq;
import com.Mud.MudameB.api.dto.response.DriverResp;

public interface IDriverService extends CrudService<DriverReq, DriverResp, Long>{
  

  public List<DriverResp> search(String name);

  public final String FIELD_BY_SORT = "price";
}
