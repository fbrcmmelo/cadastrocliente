package org.vendas.br.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.vendas.br.service.Impl.UserDetailServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserDetailServiceImpl userDetailService;

    public JwtAuthFilter(JwtService jwtService, UserDetailServiceImpl userDetailService) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader("Authorization");
        
        if (authorization != null && authorization.startsWith("Bearer")) {
            String token = authorization.split(" ")[1];
            boolean tokenIsValid = jwtService.tokenValid(token);
           
            if (tokenIsValid) {
                String usuarioLogin = jwtService.getUsuarioLogado(token);
                UserDetails userLogado = userDetailService.loadUserByUsername(usuarioLogin);

                UsernamePasswordAuthenticationToken userToken =
                        new UsernamePasswordAuthenticationToken(userLogado, null, userLogado.getAuthorities());
                userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
