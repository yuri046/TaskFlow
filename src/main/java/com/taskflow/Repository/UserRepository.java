package com.taskflow.Repository;

import com.taskflow.Entity.UserEntity;
import com.taskflow.Error.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserRepository {
    private final EntityManager em;

    public UserRepository(EntityManager em){
        this.em = em;
    }

    // Salva usuario
    public void save(UserEntity user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    //Atualiza usuario
    public void update(UserEntity user){
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    //Deleta usuario
    public void delete(UserEntity user){
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    //Retorna todos os usuarios
    public List<UserEntity> findAll(){
        return em.createNamedQuery("SELECT u FROM UsuarioEntity u", UserEntity.class).getResultList();
    }

    // busca usuario pelo ID
    public UserEntity findById(long id){
        UserEntity user = em.find(UserEntity.class, id);

        if(user == null){
            throw new ResourceNotFoundException("Usuario nao encontrado");
        }

        return user;
    }

    // busca usuario pelo email
    public UserEntity findByEmail(String email) {
        TypedQuery<UserEntity> query = em.createQuery(
                "SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class
        );
        query.setParameter("email", email);

        return query.getSingleResult();

    }
}
