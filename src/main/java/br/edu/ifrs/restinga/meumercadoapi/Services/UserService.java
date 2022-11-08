package br.edu.ifrs.restinga.meumercadoapi.Services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.restinga.meumercadoapi.dtos.UserDto;
import br.edu.ifrs.restinga.meumercadoapi.models.UserModel;
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

    public boolean isLogged() {
        Optional<UserModel> userModel = userRepository.findById(1L);
        return userModel.get().isLoged();
    }

    public boolean login(UserDto userDto) {
        Optional<UserModel> userModelOptional = userRepository.findById(1L);
        var userModel = new UserModel();

        if (userDto.getPassword().equals(userModelOptional.get().getPassword())) {
            updateUser(userModel, userModelOptional, true);
            return true;
        }

        return false;
    }

    public boolean logout() {
        Optional<UserModel> userModelOptional = userRepository.findById(1L);
        var userModel = new UserModel();

        updateUser(userModel, userModelOptional, false);
        return true;
    }

    private void updateUser(UserModel userModel, Optional<UserModel> userModelOptional, boolean loged) {
        userModel.setLoged(loged);
        userModel.setId(userModelOptional.get().getId());
        userModel.setAdmin(userModelOptional.get().isAdmin());
        userModel.setName(userModelOptional.get().getName());
        userModel.setPassword(userModelOptional.get().getPassword());
        userModel.setUserName(userModelOptional.get().getUserName());

        userRepository.save(userModel);
    }
}