package org.vendas.br.exceptions;

public class SenhaInvalidException extends RuntimeException {

    public SenhaInvalidException() {
        super("Senha Inválida !");
    }
}
