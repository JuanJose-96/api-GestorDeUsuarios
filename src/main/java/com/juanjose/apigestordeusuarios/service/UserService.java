package com.juanjose.apigestordeusuarios.service;

import com.juanjose.apigestordeusuarios.dto.CreateUserRequestDto;
import com.juanjose.apigestordeusuarios.dto.ResponseUserDto;
import com.juanjose.apigestordeusuarios.entity.User;
import com.juanjose.apigestordeusuarios.mapper.UserMapper;
import com.juanjose.apigestordeusuarios.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;


@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper= userMapper;
    }

    public ResponseUserDto addUser(CreateUserRequestDto request){
        User created = userRepository.save(userMapper.createUserToEntity(request));
        return userMapper.entityToResponseDto(created);
    }

    public User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
    }



}
