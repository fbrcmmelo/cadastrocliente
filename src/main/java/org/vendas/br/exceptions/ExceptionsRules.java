package org.vendas.br.exceptions;

//Classe responsável para tratar uma excption com uma mensagem adequada ao cliente;
public class ExceptionsRules extends RuntimeException{
    public ExceptionsRules(String message) {
        super(message);
    }
}
