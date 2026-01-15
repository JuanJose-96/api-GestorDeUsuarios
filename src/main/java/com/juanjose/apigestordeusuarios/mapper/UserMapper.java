package com.juanjose.apigestordeusuarios.mapper;

import com.juanjose.apigestordeusuarios.dto.CreateUserRequestDto;

import com.juanjose.apigestordeusuarios.dto.ResponseUserDto;
import com.juanjose.apigestordeusuarios.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public  User createUserToEntity(CreateUserRequestDto request){
        return new User(
                request.name(),
                request.surname(),
                request.age(),
                request.email(),
                request.password()
        );

    }
    public ResponseUserDto entityToResponseDto(User user){
        return new ResponseUserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getEmail()
        );
    }

}
