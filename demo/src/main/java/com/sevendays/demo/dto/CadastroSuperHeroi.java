package com.sevendays.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroSuperHeroi(
    @NotBlank String nome,
    @NotBlank String poderes,
    @NotBlank String origem,
    @NotNull String descricao) {
    }
