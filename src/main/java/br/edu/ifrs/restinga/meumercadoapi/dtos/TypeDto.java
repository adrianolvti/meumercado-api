package br.edu.ifrs.restinga.meumercadoapi.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TypeDto {
    
    @NotBlank(message = "Cannot be null, empty or blank")
    @Size(max = 70)
    private String name;
}
