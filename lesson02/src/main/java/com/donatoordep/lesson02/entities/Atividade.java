package com.donatoordep.lesson02.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String nome;

    private Double preco;

    @OneToOne(mappedBy = "atividade")
    private Categoria categoria;

    @ManyToMany
    @JoinTable(name = "participante_atividade", joinColumns =
    @JoinColumn(name = "atividade_id", referencedColumnName = "id"), inverseJoinColumns =
    @JoinColumn(name = "participante_id", referencedColumnName = "id"))
    private List<Participante> participantes = new ArrayList<>();

    @OneToMany(mappedBy = "atividade")
    private List<Bloco> blocos = new ArrayList<>();

    public Atividade() {
    }

    public Atividade(Integer id, String descricao, String nome, Double preco) {
        this.id = id;
        this.descricao = descricao;
        this.nome = nome;
        this.preco = preco;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Bloco> getBlocos() {
        return blocos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
