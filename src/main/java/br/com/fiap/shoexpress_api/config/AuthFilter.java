package br.com.fiap.shoexpress_api.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.shoexpress_api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {
    
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                // Pegar header
                var header = request.getHeader("Authorization");

                // Verificar se tem token Bearer
                if (header == null || !header.startsWith("Bearer ")){
                    filterChain.doFilter(request, response);
                    return;
                }

                try{
                    // Validar JWT
                    var jwt = header.replace("Bearer ", "");
                    var user = tokenService.getUserFromToken(jwt);
                    
                    // Autenticar
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (Exception e) {
                    SecurityContextHolder.clearContext();
                }



                filterChain.doFilter(request, response);
            }
}
