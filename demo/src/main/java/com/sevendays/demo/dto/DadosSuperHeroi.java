package com.sevendays.demo.dto;

import com.sevendays.demo.model.SuperHeroi;

public record DadosSuperHeroi(
    String nome,
    String poderes,
    String origem,
    String descricao) {
    public DadosSuperHeroi(SuperHeroi superheroi) {
        this(superheroi.getNome(), superheroi.getPoderes(), superheroi.getOrigem(), superheroi.getDescricao());
    }
}
