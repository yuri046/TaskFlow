package com.taskflow.Repository;

import com.taskflow.Entity.TaskEntity;
import com.taskflow.Entity.UserEntity;
import com.taskflow.Error.ResourceNotFoundException;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TaskRepository {
    private final EntityManager em;

    public TaskRepository(EntityManager em){
        this.em = em;
    }

    // salvar tarefa
    public void save(TaskEntity task){
        em.getTransaction().begin();
        em.persist(task);
        em.getTransaction().commit();
    }

    //Atualiza tarefa
    public void update(TaskEntity task){
        em.getTransaction();
        em.merge(task);
        em.getTransaction().commit();
    }

    // deletar tarefa
    public void delete(TaskEntity task){
        em.getTransaction().begin();
        em.remove(task);
        em.getTransaction().commit();
    }

    // retorna todas as tarefas
    public List<TaskEntity> findAll(UserEntity user){
        return em.createQuery("SELECT t FROM UserEntity WHERE t.user :user", TaskEntity.class)
                .setParameter("user", user)
                .getResultList();
    }

    // buscar tarefa pelo ID
    public TaskEntity findById(long id){
        TaskEntity task = em.find(TaskEntity.class, id);

        if(task == null){
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }

        return task;
    }
}
