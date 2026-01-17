package com.juanjose.apigestordeusuarios.controller;

import com.juanjose.apigestordeusuarios.dto.CreateUserRequestDto;
import com.juanjose.apigestordeusuarios.dto.ResponseUserDto;
import com.juanjose.apigestordeusuarios.entity.User;
import com.juanjose.apigestordeusuarios.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;


@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseUserDto> createUser(@Valid @RequestBody CreateUserRequestDto request){
        ResponseUserDto created = userService.addUser(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(location).body(created);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUser(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseUserDto> updateUser (@Valid @RequestBody CreateUserRequestDto requestDto, @PathVariable Long id){
        return ResponseEntity.ok( userService.modifyUser(requestDto,id));
    }

}
