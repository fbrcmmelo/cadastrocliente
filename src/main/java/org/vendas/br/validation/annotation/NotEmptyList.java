package org.vendas.br.validation.annotation;

import org.vendas.br.validation.NotEmptyListValidador;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//RententioPolicy.Runtime - indica que queremos que essa @Anotation seja verificada em tempo de execução
@Retention(RetentionPolicy.RUNTIME)
//ElementType.Field - indica que usaremos essa anotação em um campo
@Target(ElementType.FIELD)
//Indica que essa anotação será de validação, e indicamos em validateBy qual classe fára essa validação.
@Constraint(validatedBy = NotEmptyListValidador.class)
public @interface NotEmptyList {

    String message() default "Lista não pode estar vazia !";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
