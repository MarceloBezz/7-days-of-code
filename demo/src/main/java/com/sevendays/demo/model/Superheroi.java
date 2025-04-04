package com.sevendays.demo.model;

import com.sevendays.demo.dto.CadastroSuperHeroi;
import com.sevendays.demo.dto.DadosSuperHeroi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Superherois")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class SuperHeroi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String poderes;

    private String origem;

    private String descricao;

    public SuperHeroi(CadastroSuperHeroi dados) {
        this.nome = dados.nome();
        this.poderes = dados.poderes();
        this.origem = dados.origem();
        this.descricao = dados.descricao();
    }

    public void atualizar(DadosSuperHeroi dados) {
        if (dados.nome() != null && dados.nome() != "") {
            this.nome = dados.nome();
        }
        if (dados.poderes() != null && dados.poderes() != "") {
            this.poderes = dados.poderes();
        }
        if (dados.origem() != null && dados.origem() != "") {
            this.origem = dados.origem();
        }
        if(dados.descricao() != null && dados.descricao() != "") {
            this.descricao = dados.descricao();
        }
    }
}
