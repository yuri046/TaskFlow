package com.taskflow.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class TarefaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;
    private boolean concluida = false;

    public TarefaEntity() {
    }

    public TarefaEntity(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = LocalDateTime.now();
        this.concluida = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}










