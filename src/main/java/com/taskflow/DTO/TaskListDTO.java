package com.taskflow.DTO;

import com.taskflow.Entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;

public class TaskListDTO extends ArrayList<TaskDTO>{
    private List<TaskEntity> tasks;

    public TaskListDTO(){}
    public TaskListDTO(List<TaskEntity> tasks){
        this.tasks = tasks;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
