package org.vendas.br.util;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

//Criamos uma classe que podesse representar uma lista dos erros das exceptions para serem gerenciadas por uma ControllerAdivce
public class ApiError {
    @Getter
    List<String> errorException;

    public ApiError(List<String> errorException) {
        this.errorException = errorException;
    }

    public ApiError(String message) {
        this.errorException = Arrays.asList(message);
    }
}
