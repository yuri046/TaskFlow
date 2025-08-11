package com.taskflow.Services;

import com.taskflow.DTO.UserDTO;
import com.taskflow.Entity.UserEntity;
import com.taskflow.Error.InvalidField;
import com.taskflow.Utils.HashPassword;



import static com.taskflow.Utils.StringUtils.isNotBlank;
import static com.taskflow.Utils.StringUtils.validatePasswordFormat;

public class UserServices {
    public UserServices(){}


    public void validateCreation(UserDTO userDTO){
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
    public void convertToEntity(UserDTO userDTO, UserEntity userEntity){
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
    public void validateUpdate(UserDTO userDTO){
        boolean hasEmail = isNotBlank(userDTO.getEmail());
        boolean hasName = isNotBlank(userDTO.getName());
        boolean hasPassword = isNotBlank(userDTO.getPassword()) && validatePasswordFormat(userDTO.getPassword());

        if(!hasEmail && !hasName && !hasPassword)
        {
            throw new InvalidField("Pelo menos um dos campos deve estar preenchido!");
        }
    }


}
























