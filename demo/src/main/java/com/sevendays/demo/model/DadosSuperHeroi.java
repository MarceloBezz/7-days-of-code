package com.sevendays.demo.model;

public record DadosSuperHeroi(
    String nome,
    String poderes,
    String origem) {
    public DadosSuperHeroi(SuperHeroi superheroi) {
        this(superheroi.getNome(), superheroi.getPoderes(), superheroi.getOrigem());
    }
}
