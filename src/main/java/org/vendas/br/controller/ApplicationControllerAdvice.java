package org.vendas.br.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.vendas.br.exceptions.ExceptionsRules;
import org.vendas.br.util.ApiError;

import java.util.List;
import java.util.stream.Collectors;

/*<-- Aqui nos conseguimos fazer o tratamento das Exceptions da Response-->*/
@RestControllerAdvice
public class ApplicationControllerAdvice {
  @ExceptionHandler(ExceptionsRules.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError handleExcpetionsRules(ExceptionsRules exception) {
   String error = exception.getMessage();
   return new ApiError(error);
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError handleInvalidMethodArguments (MethodArgumentNotValidException ex) {
      List<String> invalidMessages = ex.getBindingResult()
              .getAllErrors()
              .stream()
              .map(message -> message.getDefaultMessage())
              .collect(Collectors.toList());

      return new ApiError(invalidMessages);
  }

}
