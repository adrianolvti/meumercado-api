package br.edu.ifrs.restinga.meumercadoapi.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.restinga.meumercadoapi.Services.ProductService;
import br.edu.ifrs.restinga.meumercadoapi.dtos.ProductDto;
import br.edu.ifrs.restinga.meumercadoapi.models.ProductModel;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    
    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        
        if (productService.existsByCode(productDto)) {
            return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Code already exists.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productModelOptional = productService.findById(id);

        if (!productModelOptional.isPresent()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductDto productDto) {
        Optional<ProductModel> productModelOptional = productService.findById(id);

        if (!productModelOptional.isPresent()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(productService.update(productDto, productModelOptional));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") UUID id) {
        String deletedProduct = productService.delete(id);

        if (deletedProduct.isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted " + deletedProduct + " product.");
    }

    @GetMapping({"/search-by-name/{name}"})
    public ResponseEntity<List<ProductModel>> findByName(@PathVariable(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByNameContaining(name));
    }

    @GetMapping({"/search-by-type/{type}"})
    public ResponseEntity<List<ProductModel>> findByType(@PathVariable(value = "type") String type) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByTypeNameContaining(type));
    }
}
