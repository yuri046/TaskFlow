package com.taskflow.Repository;

import com.taskflow.Entity.TarefaEntity;
import com.taskflow.Entity.UsuarioEntity;
import com.taskflow.Error.ResourceNotFoundException;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TarefaRepository {
    private EntityManager em;

    public TarefaRepository(EntityManager em){
        this.em = em;
    }

    // salvar tarefa
    public void save(TarefaEntity tarefa){
        em.getTransaction().begin();
        em.persist(tarefa);
        em.getTransaction().commit();
    }

    //Atualiza tarefa
    public void update(TarefaEntity tarefa){
        em.getTransaction();
        em.merge(tarefa);
        em.getTransaction().commit();
    }

    // deletar tarefa
    public void delete(TarefaEntity tarefa){
        em.getTransaction().begin();
        em.remove(tarefa);
        em.getTransaction().commit();
    }

    // retorna todas as tarefas
    public List<TarefaEntity> findAll(UsuarioEntity usuario){
        return em.createQuery("SELECT t FROM UsuarioEntity WHERE t.usuario :usuario", TarefaEntity.class)
                .setParameter("usuario", usuario)
                .getResultList();
    }

    // buscar tareda pelo ID
    public TarefaEntity findById(long id){
        TarefaEntity tarefa = em.find(TarefaEntity.class, id);

        if(tarefa == null){
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }

        return tarefa;
    }
}
