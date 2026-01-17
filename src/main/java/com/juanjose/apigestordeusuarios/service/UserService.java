package com.juanjose.apigestordeusuarios.service;

import com.juanjose.apigestordeusuarios.dto.CreateUserRequestDto;
import com.juanjose.apigestordeusuarios.dto.ResponseUserDto;
import com.juanjose.apigestordeusuarios.entity.User;
import com.juanjose.apigestordeusuarios.exceptions.EmailAlreadyExistsException;
import com.juanjose.apigestordeusuarios.exceptions.UserNotFoundException;
import com.juanjose.apigestordeusuarios.mapper.UserMapper;
import com.juanjose.apigestordeusuarios.repository.UserRepository;
import org.springframework.cache.interceptor.CacheOperationInvoker;
import org.springframework.cglib.core.MethodWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        if(userRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException(request.email());
        }
        User created = userMapper.createUserToEntity(request);
        userRepository.save(created);
        return userMapper.entityToResponseDto(created);
    }

    public ResponseUserDto getUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.entityToResponseDto(user);
    }

    public void removeUser(Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    public ResponseUserDto modifyUser(CreateUserRequestDto request , Long id){
        User userUpdated = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userUpdated.setName(request.name());
        userUpdated.setSurname(request.surname());
        userUpdated.setAge(request.age());
        userUpdated.setEmail(request.email());
        userUpdated.setPassword(request.password());
        return userMapper.entityToResponseDto(userRepository.save(userUpdated));
    }



}
