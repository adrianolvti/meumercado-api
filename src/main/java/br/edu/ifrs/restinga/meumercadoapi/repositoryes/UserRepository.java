package br.edu.ifrs.restinga.meumercadoapi.repositoryes;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrs.restinga.meumercadoapi.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    
}
