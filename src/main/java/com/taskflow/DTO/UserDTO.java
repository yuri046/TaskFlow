package com.taskflow.DTO;

import java.util.List;

public class UserDTO {
    private String nome;
    private String email;
    private String senha;
    private List<TaskDTO> tarefas;

    public UserDTO(String nome, String email, String senha, List<TaskDTO> tarefas) {
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

    public List<TaskDTO> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<TaskDTO> tarefas) {
        this.tarefas = tarefas;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
