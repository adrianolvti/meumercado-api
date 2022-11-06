package br.edu.ifrs.restinga.meumercadoapi.repositoryes;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.restinga.meumercadoapi.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
    boolean existsByCode(Integer code);

    List<ProductModel> findByNameContaining(String name);

    List<ProductModel> findByTypeNameContaining(String type);
}
