package com.Mud.MudameB.Domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Mud.MudameB.Domain.Entity.ReservationEntity;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>{
    
    // @Query("select r from reservation r join fetch r.user u where u.id = :idUser")
    // public Optional<ReservationEntity> findByUserId (Long idUser);
}
