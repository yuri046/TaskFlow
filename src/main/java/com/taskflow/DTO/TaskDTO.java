package com.taskflow.DTO;

import java.time.LocalDateTime;

public class TaskDTO {
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private boolean concluded;

    public TaskDTO(String title, String description, LocalDateTime creationDate, boolean concluded) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;

        this.concluded = concluded;
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

    public boolean isConcluded() {
        return concluded;
    }

    public void setConcluded(boolean concluded) {
        this.concluded = concluded;
    }
}
