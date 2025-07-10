package com.taskflow.Services;

import com.taskflow.DTO.UserDTO;
import com.taskflow.Entity.UserEntity;
import com.taskflow.Error.InvalidField;
import com.taskflow.Error.ResourceNotFoundException;
import com.taskflow.Repository.UserRepository;
import com.taskflow.Utils.HashPassword;
import io.javalin.http.Context;


import static com.taskflow.Utils.StringUtils.isNotBlank;
import static com.taskflow.Utils.StringUtils.validatePasswordFormat;

public class UserServices {
    public UserServices(){};

    public void validateCreation(UserDTO userDTO){
        boolean hasEmail = isNotBlank(userDTO.getEmail());
        boolean hasName = isNotBlank(userDTO.getName());
        boolean hasPassword = isNotBlank(userDTO.getPassword());

            if(!hasEmail || !hasName || !hasPassword) {
                throw new InvalidField("Todos os campos devem estar preenchidos");
            }

    }

    public UserEntity convertToEntity(UserDTO userDTO, UserEntity userEntity){
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setName(userDTO.getName());
        userEntity.setPassword(HashPassword.generateHash(userDTO.getPassword()));

        return userEntity;
    }

    public void validateUpdate(UserDTO userDTO){
        boolean hasEmail = isNotBlank(userDTO.getEmail());
        boolean hasName = isNotBlank(userDTO.getName());
        boolean hasPassword = isNotBlank(userDTO.getPassword()) && validatePasswordFormat(userDTO.getPassword());

        if(!hasEmail && !hasName && !hasPassword)
        {
            throw new InvalidField("Pelo menos um dos campos deve estar preenchido!");
        }
    }

    public void findUserById(UserEntity user, UserRepository repo, long id, Context ctx){
        try{
            user = repo.findById(id);
        } catch (ResourceNotFoundException e){
            ctx.status(404).result(e.getMessage());
        }
    }






}
























