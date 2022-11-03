package br.edu.ifrs.restinga.meumercadoapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidFields {
    private String fieldName;
    private String errorMessage;
}
