package br.edu.ifrs.restinga.meumercadoapi.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifrs.restinga.meumercadoapi.models.TypeModel;
import lombok.Data;

@Data
public class ProductDto {

    @NotNull
    @Max(value = 3)
    private Integer code;
    
    @NotBlank(message = "Cannot be null, empty or blank")
    @Size(max = 70)
    private String name;

    @NotNull(message = "Cannot be null, empty or blank")
    private double price;

    private Integer inventory;

    @NotNull
    private TypeModel type;
}
