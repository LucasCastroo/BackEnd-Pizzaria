package br.com.unitins.a1.service;

import br.com.unitins.a1.dto.ClienteResponseDTO;
import br.com.unitins.a1.dto.FuncionarioResponseDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@ApplicationScoped
public class JwtServiceImpl implements JwtService{

    private static final Duration TOKEN_DURATION = Duration.ofHours(24);

    @Override
    public String generateJwt(ClienteResponseDTO usuario) {
        return Jwt.issuer("pizzaria-jwt")
                .subject(usuario.email())
                .groups(Set.of("USER"))
                .expiresAt(getExpiration())
                .claim("tipo", "CLIENTE") // armazenando o tipo de conta no token
                .sign();
    }

    @Override
    public String generateJwt(FuncionarioResponseDTO usuario) {
        return Jwt.issuer("pizzaria-jwt")
                .subject(usuario.email())
                .groups(Set.of(usuario.tipoAcesso().name()))
                .expiresAt(getExpiration())
                .claim("tipo", "FUNCIONARIO") // armazenando o tipo de conta no token
                .sign();
    }

    private Instant getExpiration(){
        return Instant.now().plus(TOKEN_DURATION);
    }
}
