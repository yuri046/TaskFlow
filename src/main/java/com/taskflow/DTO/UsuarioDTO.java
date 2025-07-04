package com.taskflow.DTO;

import com.taskflow.Entity.TarefaEntity;

import java.util.List;

public class UsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private List<TarefaDTO> tarefas;

    public UsuarioDTO(String nome, String email, String senha, List<TarefaDTO> tarefas) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tarefas = tarefas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TarefaDTO> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<TarefaDTO> tarefas) {
        this.tarefas = tarefas;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
