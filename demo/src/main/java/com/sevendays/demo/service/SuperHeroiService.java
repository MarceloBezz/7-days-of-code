package com.sevendays.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevendays.demo.model.DadosSuperHeroi;
import com.sevendays.demo.model.SuperHeroi;
import com.sevendays.demo.repository.SuperHeroiRepository;

import jakarta.transaction.Transactional;

@Service
public class SuperHeroiService {
    
    @Autowired
    private SuperHeroiRepository superHeroiRepository;

    public SuperHeroi pegaSuperheroi(Long id) {
        return superHeroiRepository.findById(id).get();
    }

    public List<SuperHeroi> listarSuperherois() {
        return superHeroiRepository.findAll();
    }

    public SuperHeroi cadastrarSuperheroi(SuperHeroi superHeroi) {
        return superHeroiRepository.save(superHeroi);
    }

    @Transactional
    public SuperHeroi atualizar(Long id, DadosSuperHeroi dados) {
        SuperHeroi superheroi = superHeroiRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Super-herói não encontrado"));
        superheroi.atualizar(dados);
        return superheroi;
    }

    public void deletarSuperHeroi(Long id) throws Exception {
        var superHeroi = superHeroiRepository.existsById(id);
        if (!superHeroi) {
            throw new Exception("Super-herói não encontrado!");
        }
        superHeroiRepository.deleteById(id);
    }
}
