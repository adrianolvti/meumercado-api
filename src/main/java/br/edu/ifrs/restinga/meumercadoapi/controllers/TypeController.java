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

import br.edu.ifrs.restinga.meumercadoapi.Services.TypeService;
import br.edu.ifrs.restinga.meumercadoapi.dtos.TypeDto;
import br.edu.ifrs.restinga.meumercadoapi.models.TypeModel;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/type")
public class TypeController {
    

    @Autowired
    TypeService typeService;

    @PostMapping
    public ResponseEntity<Object> saveType(@RequestBody @Valid TypeDto typeDto) {
        if (typeService.existsByName(typeDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This name already in use!");
        }

        var typeModel = new TypeModel();
        BeanUtils.copyProperties(typeDto, typeModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(typeService.save(typeModel));
    }

    @GetMapping
    public ResponseEntity<List<TypeModel>> getAllTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(typeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneType(@PathVariable(value = "id") UUID id) {
        Optional<TypeModel> typeModelOptional = typeService.findById(id);

        if (!typeModelOptional.isPresent()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found.");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(typeModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTye(@PathVariable(value = "id") UUID id, @RequestBody @Valid TypeDto typeDto) {
        Optional<TypeModel> typeModelOptional = typeService.findById(id);

        if (!typeModelOptional.isPresent()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(typeService.update(typeDto, typeModelOptional));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteType(@PathVariable(value = "id") UUID id) {
        String deletedType = typeService.delete(id);

        if (deletedType.isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted " + deletedType + " type.");
    }
}
