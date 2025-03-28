package com.sevendays.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sevendays.demo.model.DadosSuperHeroi;
import com.sevendays.demo.model.SuperHeroi;
import com.sevendays.demo.repository.SuperHeroiRepository;
import com.sevendays.demo.service.SuperHeroiService;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/superheroi")
public class SuperHeroiController {

    @Autowired
    private SuperHeroiService superHeroiService;

    @GetMapping()
    public ResponseEntity<List<DadosSuperHeroi>> listar() {
        var superherois = superHeroiService.listarSuperherois();
        List<DadosSuperHeroi> dadosSuperherois = superherois
        .stream()
        .map(s -> new DadosSuperHeroi(s))
        .toList();

        return ResponseEntity.ok(dadosSuperherois);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosSuperHeroi> pegarPorId(@PathVariable Long id) {
        var superheroi = superHeroiService.pegaSuperheroi(id);
        var dadosSuperHeroi = new DadosSuperHeroi(superheroi);

        return ResponseEntity.ok(dadosSuperHeroi);
    }

    @PostMapping()
    public ResponseEntity<SuperHeroi> postMethodName(@RequestBody DadosSuperHeroi superHeroi, UriComponentsBuilder uriBuilder) {
        var superheroiModel = new SuperHeroi(superHeroi);
        var novoSuperheroi = superHeroiService.cadastrarSuperheroi(superheroiModel);

        var uri = uriBuilder.path("/superheroi/{id}").buildAndExpand(novoSuperheroi.getId()).toUri();

        return ResponseEntity.created(uri).body(novoSuperheroi);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity editar(@RequestBody DadosSuperHeroi superHeroi, @PathVariable Long id) {
        try {
            var superHeroiId = superHeroiService.atualizar(id, superHeroi);

            return ResponseEntity.ok(new DadosSuperHeroi(superHeroiId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        try {
            superHeroiService.deletarSuperHeroi(id);
            return ResponseEntity.ok("Super-herói deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
