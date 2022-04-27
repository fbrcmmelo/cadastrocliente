package org.vendas.br.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //esse Objeto sera uma instancia de um bean para prover a documentação das apis do projeto
    //Doc - Swagger2
    //useDefaultResponseMessages - false - Aqui se colocarmos true ele trara o o significado das responses padroes
    //Em nosso caso esta false, pois quando criamos um objeto em POST, retornamos 201 Createad e não 200 o padrao
    //Selecionar o pacote das Apis com RequestHandlerSelectors.basePackage e selecionar todas as Paths com .any()
    //Setar as info da api com apiInfo()
    @Bean
    public Docket docket () {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.vendas.br.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo () {
        return new ApiInfoBuilder()
                .title("Vendas API")
                .description("Api para persistencia de dados," +
                        " de Usuarios, Clientes, Produtos e Pedidos")
                .version("1.0")
                .contact(contact())
                .build();
    }

//    Reponsavel por prover o contato do criador da api
    private Contact contact () {
        return new Contact("Fabricio Melo",
                "http://github.com/fbrcmmelo",
                "fbrc.mmelo@gmail.com");
    }

    private ApiKey apiKey () {
        return new ApiKey("JWT",
                "Authorization",
                "header");
    }

    private SecurityContext securityContext () {
        return new SecurityContext(defaultAuth(), PathSelectors.any());
    }

    private List<SecurityReference> defaultAuth () {
        AuthorizationScope authorizationScope =
                new AuthorizationScope("global", "accessEverything");

        AuthorizationScope scope[] = new AuthorizationScope[1];
        scope[0] = authorizationScope;

        SecurityReference reference = new SecurityReference("JWT", scope);

        List<SecurityReference> references = new ArrayList<>();
        references.add(reference);

        return references;
    }
}
