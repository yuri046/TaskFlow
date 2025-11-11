package com.taskflow.Services;

import com.taskflow.DTO.LoginRequestDTO;
import com.taskflow.DTO.RegisterDTO;

import com.taskflow.DTO.UserDTO;
import com.taskflow.Entity.UserEntity;
import com.taskflow.Error.InvalidField;
import com.taskflow.Error.ResourceNotFoundException;
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

    public void createUser(RegisterDTO dto){
        validateCreation(dto);
        UserEntity newUser = new UserEntity();
        convertToEntity(dto, newUser);
        repo.save(newUser);
    }

    public UserDTO getUser(LoginRequestDTO login) {
        UserEntity user = repo.findByEmail(login.getEmail());
        if (user == null || !HashPassword.checkPassword(login.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Credenciais inválidas");
        }

        UserDTO dto = new UserDTO();
        convertToDto(user, dto);
        return dto;
    }

    public UserDTO getUserById(long id){
        UserEntity user = repo.findById(id);
        UserDTO dto = new UserDTO();
        convertToDto(user, dto);

        return dto;
    }

    public UserDTO updateUser(UserDTO dto, long id){
        UserEntity user = repo.findById(id);
        validateUpdate(dto);
        convertToEntity(dto, user);
        repo.update(user);
        convertToDto(user, dto);
        return dto;
    }

    public void deleteUser(long id){
        try {
            UserEntity user = repo.findById(id);
            repo.delete(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private void validateCreation(RegisterDTO userDTO){
        boolean hasEmail = isNotBlank(userDTO.getEmail());
        boolean hasName = isNotBlank(userDTO.getName());
        boolean hasPassword = isNotBlank(userDTO.getPassword());

        if(!hasEmail){
            throw new InvalidField("O campo e-mail é obrigatório!");
        }
        if(!hasName){
            throw new InvalidField("O campo nome é obrigatório!");
        }
        if (!hasPassword || !validatePasswordFormat(userDTO.getPassword())) {
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

    private void convertToEntity(RegisterDTO userDTO, UserEntity userEntity){
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

        dto.setId(user.getId());
        if(hasEmail){dto.setEmail(user.getEmail());}
        if(hasName){dto.setName(user.getName());}
        if(hasPassword){dto.setPassword(user.getPassword());}
    }

}
























