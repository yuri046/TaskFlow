package com.taskflow.DTO;

import java.util.List;

public class UserDTO {
    private String name;
    private String email;
    private String password;
    private List<TaskDTO> tasks;

    public UserDTO(String name, String email, String password, List<TaskDTO> tasks) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTarefas(List<TaskDTO> tarefas) {
        this.tasks = tasks;
    }

    public String getPassword() {
        return password;
    }

    public void setSenha(String password) {
        this.password = password;
    }
}
