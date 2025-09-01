package com.taskflow.Services;

import com.taskflow.DTO.UserDTO;
import com.taskflow.Entity.UserEntity;
import com.taskflow.Error.InvalidField;
import com.taskflow.Repository.UserRepository;
import com.taskflow.Utils.HashPassword;
import jakarta.persistence.EntityManager;


import static com.taskflow.Utils.StringUtils.isNotBlank;
import static com.taskflow.Utils.StringUtils.validatePasswordFormat;

public class UserServices {
    private final UserRepository repo;
    public UserServices(EntityManager em){
        this.repo = new UserRepository(em);
    }

    public void createUser(UserDTO dto){
        validateCreation(dto);
        UserEntity newUser = new UserEntity();

        convertToEntity(dto, newUser);
        repo.save(newUser);
    }

    public void updateUser(UserDTO dto, long id){
        UserEntity user = repo.findById(id);
        validateUpdate(dto);
        convertToEntity(dto, user);
        repo.update(user);
    }

    public void deleteUser(long id){
        UserEntity user = repo.findById(id);
        repo.delete(user);
    }


    private void validateCreation(UserDTO userDTO){
        boolean hasEmail = isNotBlank(userDTO.getEmail());
        boolean hasName = isNotBlank(userDTO.getName());
        boolean hasPassword = isNotBlank(userDTO.getPassword());

        if(!hasEmail){
            throw new InvalidField("O campo e-mail é obrigatório!");
        }
        if(!hasName){
            throw new InvalidField("O campo nome é obrigatório!");
        }
        if (!hasPassword){
            throw new InvalidField("O campo senha é obrigatório e deve ter um formato válido!");
        }
    }

    /*
     *  Converte um dto recebida do usuario
     *  para uma entity que será salva no banco de dados
     */
    private void convertToEntity(UserDTO userDTO, UserEntity userEntity){
        boolean hasEmail = isNotBlank(userDTO.getEmail());
        boolean hasName = isNotBlank(userDTO.getName());
        boolean hasPassword = isNotBlank(userDTO.getPassword()) && validatePasswordFormat(userDTO.getPassword());

        if (hasEmail){
            userEntity.setEmail(userDTO.getEmail());
        }
        if (hasName){
            userEntity.setName(userDTO.getName());
        }
        if (hasPassword){
            userEntity.setPassword(HashPassword.generateHash(userDTO.getPassword()));
        }
    }

    /*
    * Valida se os dados do dto recebido do usuario
    * não estão em branco ou são nulos
    *
    * */
    private void validateUpdate(UserDTO userDTO){
        boolean hasEmail = isNotBlank(userDTO.getEmail());
        boolean hasName = isNotBlank(userDTO.getName());
        boolean hasPassword = isNotBlank(userDTO.getPassword()) && validatePasswordFormat(userDTO.getPassword());

        if(!hasEmail && !hasName && !hasPassword)
        {
            throw new InvalidField("Pelo menos um dos campos deve estar preenchido!");
        }
    }

    private void convertToDto(UserEntity user, UserDTO dto){
        boolean hasEmail = isNotBlank(user.getEmail());
        boolean hasName = isNotBlank(user.getName());
        boolean hasPassword = isNotBlank(user.getPassword());

        if(hasEmail){dto.setEmail(user.getEmail());}
        if(hasName){dto.setName(user.getName());}
        if(hasPassword){dto.setSenha(user.getPassword());}


    }


}
























