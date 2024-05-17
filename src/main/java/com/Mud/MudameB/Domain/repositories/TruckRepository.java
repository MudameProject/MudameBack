package com.Mud.MudameB.Domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Mud.MudameB.Domain.Entity.TruckEntity;

@Repository

public interface TruckRepository extends JpaRepository<TruckEntity, Long>{
    
}
