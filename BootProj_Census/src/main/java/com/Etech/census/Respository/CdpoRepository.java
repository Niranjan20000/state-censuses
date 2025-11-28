package com.Etech.census.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Etech.census.entity.Cdpo;

public interface CdpoRepository extends JpaRepository<Cdpo, Long> {

    Optional<Cdpo> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByMobile(String mobile);

}
