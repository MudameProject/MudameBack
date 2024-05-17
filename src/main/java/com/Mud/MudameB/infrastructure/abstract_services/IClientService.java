package com.Mud.MudameB.infrastructure.abstract_services;

import com.Mud.MudameB.api.dto.request.ClientReq;
import com.Mud.MudameB.api.dto.response.ClientResp;

public interface IClientService extends CrudService<ClientReq, ClientResp, Long> {
    public String FIELD_BY_SORT = "name";
}
