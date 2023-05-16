package com.krillinator.springSecurityLektion.rats;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krillinator.springSecurityLektion.user.UserModel;

public interface RatRepository extends JpaRepository<Rat, Long> {
    
    List<Rat> findByOwner(UserModel owner);

}
