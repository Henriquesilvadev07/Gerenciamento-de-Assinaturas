package com.example.Controle_de_Assinaturas.Security;
import com.example.Controle_de_Assinaturas.repository.UsersRepository;
import com.example.Controle_de_Assinaturas.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UsersRepository usersRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            var user = usersRepository.findByLogin(subject);
            //pega a autorizacao do usuario
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            //diz ao SpringSecurity que o usuario tem a permissao de utilizar aquilo que foi dado permissao
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    //funcao para fazer a limpeza do header recebido, remover qualquer palavra alem do token e recuperar o token
    public String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader!= null && authorizationHeader.startsWith("Bearer ")){
            return authorizationHeader.substring(7).trim();
        }
        return null;
    }
}