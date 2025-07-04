package com.taskflow.DTO;

import com.taskflow.Entity.TarefaEntity;

import java.util.List;

public class UsuarioDTO {
    private String nome;
    private List<TarefaEntity> tarefas;

    public UsuarioDTO(String nome, List<TarefaEntity> tarefas){
        this.nome = nome;
        this.tarefas = tarefas;
    }
}
