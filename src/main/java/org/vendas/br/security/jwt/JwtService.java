package org.vendas.br.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vendas.br.model.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

//Nessa Classe iremos implementar o serviço de geração de token, a classe represeta a entidade token com seus atributos

@Service
public class JwtService {

    @Value("${campo.expiracao.token}")
    private String expiracao;

    @Value("${campo.chave.assinatura.token}")
    private String assinatura;

//  Esse metodo retornara uma string de um builder do token da classe Jwts
    public String getToken (Usuario usuario) {

//      Agora precisamos pegar a hora da data atual e adicionar o tempo de expiracao.
        LocalDateTime duracaoExpiracao = LocalDateTime.now().plusMinutes(Long.valueOf(expiracao));

//      Entao temos que converter a expiracao em um objeto instant para usar como parametro da Classe Date,
//      pois o builder de Jwts recebe um obeto Date para setExpiration
        Date dataExpiracao = Date.from(Instant.from(duracaoExpiracao.atZone(ZoneId.systemDefault())));

//      HashMap<String, Object> claims = new HashMap<>();
//      claims.put("emaildousuario", "usuario@gmail.com");
//      claims.put("enderecousuario", "rua 10, numero 05");

        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setExpiration(dataExpiracao)
                //.setClaims(claims) podemos adicionar mais informaçoes com o claims
                .signWith(SignatureAlgorithm.HS512, assinatura)
                .compact();
//      e por fim passamos os parametros, nosso subject o identificador do usuario
//      a expiracao do tipo date, um algoritimo de assinatura e nossa chave-assinatura
    }

//  Esse metodo é reponsavel para obter informaçoes ou claims de um token
    private Claims getClaims (String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(assinatura)
                .parseClaimsJws(token)
                .getBody();
    }

//  Metodo responsavel para verificar se um token não esta expirado
    public boolean tokenValid (String token) {
        try {
            Claims claims = getClaims(token);
            Date expiration = claims.getExpiration();
            LocalDateTime dataExpiracao = expiration.toInstant().
                    atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(dataExpiracao);
        } catch (Exception e){
            return false;
        }
    }

//  Trazer o subject do token
    public String getUsuarioLogado (String token) throws ExpiredJwtException {
        return (String) getClaims(token).getSubject();
    }

}
