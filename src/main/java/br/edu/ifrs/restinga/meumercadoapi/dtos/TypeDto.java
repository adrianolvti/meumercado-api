package br.edu.ifrs.restinga.meumercadoapi.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TypeDto {
    
    @NotBlank(message = "Não deve ser nulo, vazio ou em branco")
    @Size(max = 70)
    private String name;
}
