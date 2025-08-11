package com.taskflow.Services;

import com.taskflow.DTO.TaskDTO;
import com.taskflow.Entity.TaskEntity;
import com.taskflow.Entity.UserEntity;
import com.taskflow.Error.InvalidField;

import java.time.LocalDateTime;

import static com.taskflow.Utils.StringUtils.isNotBlank;

public class TaskServices {

    /* Metodo construtor */
    public TaskServices(){}

    /*
    *  Converte um dto recebido do usuario
    *  para uma entity que será salva no banco de dados
    */
    public void convertToEntity(TaskDTO dto, TaskEntity task, UserEntity user){
        boolean hasTitle = isNotBlank(dto.getTitle());
        boolean hasDescription = isNotBlank((dto.getDescription()));

        if (hasTitle){
            task.setTitle(dto.getTitle());
        }
        if (hasDescription){
            task.setDescription(dto.getDescription());
        }

        task.setUser(user);
    }

    /*
    *  Valida se os dados recebidos pelo dto
    *  estão de acordo com a regra de negocio para criação
    *
    */

    public void validateCreation(TaskDTO dto){
        boolean hasTitle = isNotBlank(dto.getTitle());
        if(!hasTitle){
            throw new InvalidField("Titulo deve estar preenchido");
        }

        dto.setCreationDate(LocalDateTime.now());
    }

    /*
    * Valida se os dados recebidos pelo dto
    * estão de acordo com a regra de negocio para update
    * */
    public void validateUpdate(TaskDTO dto, TaskEntity task){
        boolean hasTitle = isNotBlank(dto.getTitle());
        boolean hasDescription = isNotBlank((dto.getDescription()));

        if(!hasTitle && !hasDescription){
            throw new InvalidField("Pelo menos um dos campos deve estar preenchido!");
        }
    }

    /*
    *
    * */
    public void updateConclusionStatus(TaskEntity task, TaskDTO dto){
        if(dto.isConcluded() != task.isConcluded()){
            task.setConcluded(dto.isConcluded());
        }
    }
}
