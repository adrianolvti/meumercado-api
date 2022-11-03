package br.edu.ifrs.restinga.meumercadoapi.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.restinga.meumercadoapi.dtos.ProductDto;
import br.edu.ifrs.restinga.meumercadoapi.models.ProductModel;
import br.edu.ifrs.restinga.meumercadoapi.repositoryes.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;
    
    @Transactional
    public ProductModel save(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public Optional<ProductModel> findById(UUID id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Object update(ProductDto productDto, Optional<ProductModel> productModelOptional) { 
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);

        productModel.setId(productModelOptional.get().getId());   
        return this.save(productModel);
    }

    @Transactional
    public String delete(UUID id) {
        Optional<ProductModel> productModelOptional = this.findById(id);

        if (!productModelOptional.isPresent()) {
            return null;    
        }

        String productName = productModelOptional.get().getName();
        productRepository.delete(productModelOptional.get());
        return productName;
    }
}
