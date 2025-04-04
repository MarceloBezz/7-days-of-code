package com.sevendays.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevendays.demo.model.DadosSuperHeroi;
import com.sevendays.demo.model.SuperHeroi;
import com.sevendays.demo.repository.SuperHeroiRepository;


@Service
public class SuperHeroiService {
    
    @Autowired
    private SuperHeroiRepository superHeroiRepository;

    public DadosSuperHeroi pegaSuperheroi(Long id) {
        var superHeroi = superHeroiRepository.findById(id).get();
        return new DadosSuperHeroi(superHeroi);
    }

    public List<DadosSuperHeroi> listarSuperherois() {
        return superHeroiRepository.findAll().stream()
        .map(s -> new DadosSuperHeroi(s))
        .toList();
    }

    public SuperHeroi cadastrarSuperheroi(DadosSuperHeroi dto) throws Exception {
        var superHeroiJaCadastrado = superHeroiRepository.existsByNome(dto.nome());

        if (superHeroiJaCadastrado) {
            throw new Exception("Super-Herói já cadastrado!");
        }

        return superHeroiRepository.save(new SuperHeroi(dto));
    }

    public DadosSuperHeroi atualizar(Long id, DadosSuperHeroi dados) {
        SuperHeroi superheroi = superHeroiRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Super-herói não encontrado"));
        superheroi.atualizar(dados);

        return new DadosSuperHeroi(superheroi);
    }

    public void deletarSuperHeroi(Long id) throws Exception {
        var superHeroi = superHeroiRepository.existsById(id);
        if (!superHeroi) {
            throw new Exception("Super-herói não encontrado!");
        }
        
        superHeroiRepository.deleteById(id);
    }
}
