package com.Mudame.Mudame.repositories;

import com.Mudame.Mudame.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
//carlox "no estoy seguro si se debe poner integer aqui"
public interface UserRepository extends JpaRepository<User, Integer>{
    
}
