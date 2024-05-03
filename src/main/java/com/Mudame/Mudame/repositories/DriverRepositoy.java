package com.Mudame.Mudame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Mudame.Mudame.Entity.Driver;

@Repository
//carlox "no estoy seguro si se debe poner integer aqui"
public interface DriverRepositoy extends JpaRepository<Driver, Integer> {
    
}
