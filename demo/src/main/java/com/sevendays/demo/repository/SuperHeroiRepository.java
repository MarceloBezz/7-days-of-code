package com.sevendays.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevendays.demo.model.SuperHeroi;
import java.util.List;


public interface SuperHeroiRepository extends JpaRepository<SuperHeroi, Long>{

    SuperHeroi findByNomeAndOrigem(String nome, String origem);

    boolean existsByNome(String nome);

    List<SuperHeroi> findByPoderesContainingIgnoreCase(String poder);
}
