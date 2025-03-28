package com.sevendays.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevendays.demo.model.Superheroi;

public interface SuperHeroiRepository extends JpaRepository<Superheroi, Long>{
    
}
