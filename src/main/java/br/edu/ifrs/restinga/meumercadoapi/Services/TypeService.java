package br.edu.ifrs.restinga.meumercadoapi.Services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.restinga.meumercadoapi.dtos.TypeDto;
import br.edu.ifrs.restinga.meumercadoapi.models.TypeModel;
import br.edu.ifrs.restinga.meumercadoapi.repositoryes.TypeRepository;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;
    
    @Transactional
    public TypeModel save(TypeModel typeModel) {
        return typeRepository.save(typeModel);
    }

    public List<TypeModel> findAll() {
        return typeRepository.findAll();
    }

    public Optional<TypeModel> findById(Long id) {
        return typeRepository.findById(id);
    }

    @Transactional
    public Object update(TypeDto typeDto, Optional<TypeModel> typeModelOptional) { 
        var typeModel = new TypeModel();
        BeanUtils.copyProperties(typeDto, typeModel);

        typeModel.setId(typeModelOptional.get().getId());   
        return this.save(typeModel);
    }

    @Transactional
    public String delete(Long id) {
        Optional<TypeModel> typeModelOptional = this.findById(id);

        if (!typeModelOptional.isPresent()) {
            return null;
        }

        String typeName = typeModelOptional.get().getName();
        typeRepository.delete(typeModelOptional.get());
        return typeName;
    }

    public boolean existsByName(String name) {
        return typeRepository.existsByName(name);
    }
}
