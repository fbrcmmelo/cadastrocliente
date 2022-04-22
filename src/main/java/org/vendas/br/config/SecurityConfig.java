package org.vendas.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vendas.br.service.Impl.UserDetailServiceImpl;

import java.util.Random;

//Aqui vamos criar as configurações para segurança de autenticação e acesso da aplicação
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceImpl userService;
    //Aqui nos usamos um metodo do tipo PasswordEnconder para gerar nossas formas de criptografia de senhas
    @Bean
    public PasswordEncoder passwordEncoder () {
        //BcryptPasswordEncoder é um encriptador de senhas gerando um código hash diferente vezes que usado para as senhas.
        return new BCryptPasswordEncoder();
    }

    //Aqui nos extendemos de WebSecurityConfigurerAdapter o metodo responsavel para configurar a autenticação de usuarios
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth
           .userDetailsService(userService)
               .passwordEncoder(passwordEncoder());
    }

    //Aqui nos iremos extender da mesma classe acima o metodo responsavel para configurar o acesso aos endpoints aos usuários
    //Podemos usar tipos de exeções de acesso, como por roles, authorize, authentic
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/clientes/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/pedidos/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/produtos/**")
                        .hasRole("ADMIN")
                .and()
                    .httpBasic();
    }
}
