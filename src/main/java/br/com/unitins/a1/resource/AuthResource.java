package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.ClienteResponseDTO;
import br.com.unitins.a1.dto.LoginDTO;
import br.com.unitins.a1.dto.FuncionarioResponseDTO;
import br.com.unitins.a1.service.ClienteService;
import br.com.unitins.a1.service.FuncionarioService;
import br.com.unitins.a1.service.HashService;
import br.com.unitins.a1.service.JwtService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    @Inject
    ClienteService clienteService;
    @Inject
    FuncionarioService funcionarioService;
    @Inject
    HashService hashService;
    @Inject
    JwtService jwtService;

    @POST
    @Path("/cliente")
    public Response loginCliente(@Valid LoginDTO dto){
        String hashed = hashService.getHash(dto.senha());
        ClienteResponseDTO usuario = clienteService.findByEmailSenha(dto.email(), hashed);
        String token = jwtService.generateJwt(usuario);
        return Response.ok().header("Authorization", token).build();
    }

    @POST
    @Path("/funcionario")
    public Response loginFuncionario(@Valid LoginDTO dto){
        String hashed = hashService.getHash(dto.senha());
        FuncionarioResponseDTO usuario = funcionarioService.findByEmailSenha(dto.email(), hashed);
        String token = jwtService.generateJwt(usuario);
        return Response.ok().header("Authorization", token).build();
    }
}
