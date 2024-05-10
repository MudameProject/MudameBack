package com.Mud.MudameB.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

import com.Mud.MudameB.Utils.enums.Capacity;
import com.Mud.MudameB.api.dto.request.TruckReq;
import com.Mud.MudameB.api.dto.response.TruckResp;

public interface ITruckService extends CrudService<TruckReq, TruckResp, Long> {

    public Page<TruckResp> getAll(int page, int size, Capacity CrudCapacity);

  public final String CAPACITY_SEARCH = "capacity";

  
}
