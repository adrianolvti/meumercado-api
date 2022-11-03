package br.edu.ifrs.restinga.meumercadoapi.repositoryes;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.restinga.meumercadoapi.models.TypeModel;

@Repository
public interface TypeRepository extends JpaRepository<TypeModel, UUID> {
    boolean existsByName(String name);
}
