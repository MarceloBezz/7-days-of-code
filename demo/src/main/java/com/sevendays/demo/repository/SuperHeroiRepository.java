package com.sevendays.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevendays.demo.model.SuperHeroi;

public interface SuperHeroiRepository extends JpaRepository<SuperHeroi, Long>{

    SuperHeroi findByNomeAndOrigem(String nome, String origem);
    
}
