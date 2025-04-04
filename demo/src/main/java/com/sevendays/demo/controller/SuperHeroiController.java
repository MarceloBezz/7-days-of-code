package com.sevendays.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sevendays.demo.dto.CadastroSuperHeroi;
import com.sevendays.demo.dto.DadosSuperHeroi;
import com.sevendays.demo.service.SuperHeroiService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/superheroi")
public class SuperHeroiController {

    @Autowired
    private SuperHeroiService superHeroiService;

    @SuppressWarnings("rawtypes")
    @GetMapping()
    public ResponseEntity listar() {
        try {
            var superherois = superHeroiService.listarSuperherois();
    
            return ResponseEntity.ok(superherois);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/{id}")
    public ResponseEntity pegarPorId(@PathVariable Long id) {
        try {
            var superheroi = superHeroiService.pegaSuperheroi(id);
    
            return ResponseEntity.ok(superheroi);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping()
    public ResponseEntity postMethodName(@RequestBody @Valid CadastroSuperHeroi superHeroi, UriComponentsBuilder uriBuilder) {
        try {
            var novoSuperheroi = superHeroiService.cadastrarSuperheroi(superHeroi);
            var uri = uriBuilder.path("/superheroi/{id}").buildAndExpand(novoSuperheroi.getId()).toUri();
    
            return ResponseEntity.created(uri).body(new DadosSuperHeroi(novoSuperheroi));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @SuppressWarnings("rawtypes")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity editar(@RequestBody DadosSuperHeroi superHeroi, @PathVariable Long id) {
        try {
            var superHeroiId = superHeroiService.atualizar(id, superHeroi);

            return ResponseEntity.ok(superHeroiId);
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

    @SuppressWarnings("rawtypes")
    @GetMapping("/filtro")
    public ResponseEntity filtrarPorPoderes(@RequestParam String poder) {
        var superHeroisFiltrados = superHeroiService.filtrarPorPoderes(poder);
        if (superHeroisFiltrados == null) {
            return ResponseEntity.badRequest().body("Nenhum super-herói com o poder digitado!");
        }
        return ResponseEntity.ok(superHeroisFiltrados);
    }
    
}
