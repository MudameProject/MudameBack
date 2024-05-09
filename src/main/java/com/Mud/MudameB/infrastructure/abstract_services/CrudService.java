package com.Mud.MudameB.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

import com.Mud.MudameB.Utils.enums.SortType;

public interface CrudService<RQ,RS,ID> {
    public RS create(RQ request);

    public RS get(ID id);

    public RS update(RQ request, ID id);

    public Void delete (ID id);

    public Page<RS> getAll(int Page, int size, SortType sortType);
}