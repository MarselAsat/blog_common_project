package com.example.user_service.user.controller;

import com.example.user_service.user.dto.UserCreateDto;
import com.example.user_service.user.dto.UserDto;
import com.example.user_service.user.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final KeycloakService keycloakService;

    @Value("${eureka.instance.instanceId}")
    private String instanceId;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id){
        return ResponseEntity.ok(new UserDto(keycloakService.getUsername(id)));
    }

    public ResponseEntity<String> create(@RequestBody UserCreateDto userDto){
        log.info("Using instance: " + instanceId);
        String userId = keycloakService.createUser(userDto);
        return ResponseEntity.ok(userId);
    }

}
