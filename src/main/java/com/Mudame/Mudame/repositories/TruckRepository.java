package com.Mudame.Mudame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.sound.midi.Track;

@Repository
//carlox "no estoy seguro si se debe poner integer aqui"
public interface TruckRepository extends JpaRepository<Track, Integer>{
    
}
