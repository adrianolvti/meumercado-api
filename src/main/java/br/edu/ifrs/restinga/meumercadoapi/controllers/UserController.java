package br.edu.ifrs.restinga.meumercadoapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.restinga.meumercadoapi.Services.UserService;
import br.edu.ifrs.restinga.meumercadoapi.dtos.UserDto;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> isAdmin(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.isAdmin(userDto));
    }
}
