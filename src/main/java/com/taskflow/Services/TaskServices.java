package com.taskflow.Services;

import com.taskflow.DTO.TaskDTO;
import com.taskflow.DTO.TaskListDTO;
import com.taskflow.Entity.TaskEntity;
import com.taskflow.Entity.UserEntity;
import com.taskflow.Error.InvalidField;
import com.taskflow.Repository.TaskRepository;
import com.taskflow.Repository.UserRepository;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static com.taskflow.Utils.StringUtils.isNotBlank;

public class TaskServices {
    private final TaskRepository taskRepo;
    private final UserRepository userRepo;

    public TaskServices(EntityManager em){
        this.taskRepo = new TaskRepository(em);
        this.userRepo = new UserRepository(em);
    }

    public void createTask(TaskDTO dto, long id){
        validateCreation(dto);
        TaskEntity task = new TaskEntity();
        UserEntity user = userRepo.findById(id);

        convertToEntity(dto, task, user);
        task.setCreationDate(LocalDateTime.now());
        taskRepo.save(task);
    }

    public TaskListDTO getAllTasks(long id){
        UserEntity user = userRepo.findById(id);
        List<TaskEntity> tasks = taskRepo.findAll(user);
        TaskListDTO tasksDto = new TaskListDTO();
        for (TaskEntity t : tasks){
            TaskDTO dto = new TaskDTO();
            convertToDto(t, dto);
            tasksDto.add(dto);
        }

        return tasksDto;
    }

    public TaskDTO getTask(long id){
        TaskEntity task = taskRepo.findById(id);
        TaskDTO dto = new TaskDTO();
        convertToDto(task, dto);

        return dto;
    }

    public TaskDTO updateTask(TaskDTO dto, long userID, long taskID){
        validateUpdate(dto);
        UserEntity user = userRepo.findById(userID);
        TaskEntity task = taskRepo.findById(taskID);
        convertToEntity(dto,task,user);
        updateConclusionStatus(task, dto);
        taskRepo.update(task);

        return dto;
    }

    public void deleteTask(long id){
        TaskEntity task = taskRepo.findById(id);
        taskRepo.delete(task);
    }

    /*
    *  Converte um dto recebido do usuario
    *  para uma entity que será salva no banco de dados
    */
    private void convertToEntity(TaskDTO dto, TaskEntity task, UserEntity user){
        boolean hasTitle = isNotBlank(dto.getTitle());
        boolean hasDescription = isNotBlank(dto.getDescription());


        if (hasTitle){
            task.setTitle(dto.getTitle());
        }
        if (hasDescription){
            task.setDescription(dto.getDescription());
        }

        task.setConclusionDate(dto.getConclusionDate());
        task.setUser(user);
    }

    /*
    *  Valida se os dados recebidos pelo dto
    *  estão de acordo com a regra de negocio para criação
    *
    */

    private void validateCreation(TaskDTO dto){
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
    private void validateUpdate(TaskDTO dto){
        boolean hasTitle = isNotBlank(dto.getTitle());
        boolean hasDescription = isNotBlank((dto.getDescription()));
        boolean isConcluded = dto.isConcluded();

        if(!hasTitle && !hasDescription && !isConcluded){
            throw new InvalidField("Pelo menos um dos campos deve estar preenchido!");
        }
    }

    private void updateConclusionStatus(TaskEntity task, TaskDTO dto){
        if(dto.isConcluded() != task.isConcluded()){
            task.setConcluded(dto.isConcluded());
            task.setConclusionDate(LocalDateTime.now());
        }
    }

    private void convertToDto(TaskEntity task, TaskDTO dto){
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCreationDate(task.getCreationDate());
        dto.setConclusionDate(task.getConclusionDate());
        dto.setConcluded(task.isConcluded());

    }
}
