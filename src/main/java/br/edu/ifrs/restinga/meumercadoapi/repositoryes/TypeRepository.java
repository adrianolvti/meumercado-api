package br.edu.ifrs.restinga.meumercadoapi.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.restinga.meumercadoapi.models.TypeModel;

@Repository
public interface TypeRepository extends JpaRepository<TypeModel, Long> {
    boolean existsByName(String name);
}
