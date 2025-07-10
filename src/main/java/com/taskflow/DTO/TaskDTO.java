package com.taskflow.DTO;

import java.time.LocalDateTime;

public class TaskDTO {
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime conclusionDate;
    private boolean concluida;

    public TaskDTO(String title, String description, LocalDateTime creationDate, LocalDateTime conclusionDate, boolean concluida) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.conclusionDate = conclusionDate;
        this.concluida = concluida;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titulo) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = this.creationDate;
    }

    public LocalDateTime getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(LocalDateTime conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
