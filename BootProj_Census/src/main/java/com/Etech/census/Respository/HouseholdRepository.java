package com.Etech.census.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Etech.census.entity.Household;

public interface HouseholdRepository extends JpaRepository<Household, Long> {

   
}


