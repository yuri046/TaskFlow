package com.taskflow.Repository;

import com.taskflow.Entity.UsuarioEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UsuarioRepository {
    private EntityManager em;

    public UsuarioRepository(EntityManager em){
        this.em = em;
    }

    // Salva usuario
    public void save(UsuarioEntity usuario){
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    //Atualiza usuario
    public void update(UsuarioEntity usuario){
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    //Deleta usuario
    public void delete(UsuarioEntity usuario){
        em.getTransaction().begin();
        em.remove(usuario);
        em.getTransaction().commit();
    }

    //Retorna todos os usuarios
    public List<UsuarioEntity> findAll(){
        return em.createNamedQuery("SELECT u FROM UsuarioEntity u", UsuarioEntity.class).getResultList();
    }

    // retorna usuario especifico pelo ID
    public UsuarioEntity findById(long id){
        return em.find(UsuarioEntity.class, id);
    }


}
