package com.Mud.MudameB.infrastructure.abstract_services;

import com.Mud.MudameB.api.dto.request.ClientRegiserReq;
import com.Mud.MudameB.api.dto.request.LoginReq;
import com.Mud.MudameB.api.dto.request.RegisterReq;
import com.Mud.MudameB.api.dto.response.AuthResp;

public interface IAuthService {

    public AuthResp login(LoginReq request);

    public AuthResp register(RegisterReq request);

    public AuthResp registerClient(ClientRegiserReq request);
}