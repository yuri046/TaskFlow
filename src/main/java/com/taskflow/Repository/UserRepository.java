package com.taskflow.Repository;

import com.taskflow.Entity.UserEntity;
import com.taskflow.Error.ResourceNotFoundException;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserRepository {
    private EntityManager em;

    public UserRepository(EntityManager em){
        this.em = em;
    }

    // Salva usuario
    public void save(UserEntity usuario){
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    //Atualiza usuario
    public void update(UserEntity usuario){
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    //Deleta usuario
    public void delete(UserEntity usuario){
        em.getTransaction().begin();
        em.remove(usuario);
        em.getTransaction().commit();
    }

    //Retorna todos os usuarios
    public List<UserEntity> findAll(){
        return em.createNamedQuery("SELECT u FROM UsuarioEntity u", UserEntity.class).getResultList();
    }

    // retorna usuario especifico pelo ID
    public UserEntity findById(long id){
        UserEntity usuario = em.find(UserEntity.class, id);

        if(usuario == null){
            throw new ResourceNotFoundException("Usuario nao encontrado");
        }

        return usuario;
    }


}
