package br.edu.ifrs.restinga.meumercadoapi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.restinga.meumercadoapi.dtos.UserDto;
import br.edu.ifrs.restinga.meumercadoapi.repositoryes.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public boolean isAdmin(UserDto userDto) {
        System.out.println(
            userDto.getPassword()
        );

        if (userDto.getPassword().equals("Abc@1234")) {
            return true;
        }

        return false;
    }
}