package com.Mud.MudameB.infrastructure.abstract_services;

import com.Mud.MudameB.api.dto.request.UserReq;
import com.Mud.MudameB.api.dto.response.UserResp;

public interface IUserService extends CrudService<UserReq, UserResp, Long> {
    public String FIELD_BY_SORT = "name";
}
