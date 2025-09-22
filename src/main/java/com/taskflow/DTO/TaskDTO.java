package com.taskflow.DTO;

import java.time.LocalDateTime;

public class TaskDTO {
    private long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime conclusionDate;
    private boolean concluded;

    public TaskDTO(){}
    public TaskDTO(String title, String description, LocalDateTime creationDate, LocalDateTime conclusionDate, boolean concluded) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.conclusionDate = conclusionDate;
        this.concluded = concluded;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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
        this.creationDate = creationDate;
    }

    public LocalDateTime getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(LocalDateTime conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public boolean isConcluded() {
        return concluded;
    }

    public void setConcluded(boolean concluded) {
        this.concluded = concluded;
    }
}
