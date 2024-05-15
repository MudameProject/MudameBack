package com.Mud.MudameB.infrastructure.abstract_services;

import com.Mud.MudameB.api.dto.request.ReservationReq;
import com.Mud.MudameB.api.dto.response.ReservationResp;

import java.util.List;

public interface IReservationService extends CrudService<ReservationReq, ReservationResp, Long> {
    public List<ReservationResp> search(String name);
}
