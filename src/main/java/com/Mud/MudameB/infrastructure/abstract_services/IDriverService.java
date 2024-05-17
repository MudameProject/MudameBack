package com.Mud.MudameB.infrastructure.abstract_services;

import java.util.List;

import com.Mud.MudameB.api.dto.request.DriverReq;
import com.Mud.MudameB.api.dto.response.DriverResp;

public interface IDriverService extends CrudService<DriverReq, DriverResp, Long> {

  public List<DriverResp> search(String name);


}
