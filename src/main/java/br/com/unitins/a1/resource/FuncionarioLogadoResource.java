package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.AlterarSenhaDTO;
import br.com.unitins.a1.service.FuncionarioService;
import br.com.unitins.a1.service.HashService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/meu-perfil")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"FUNCIONARIO"})
public class FuncionarioLogadoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    FuncionarioService funcionarioService;

    @Inject
    HashService hashService;

    @GET
    public Response minhaConta() {
        return Response.ok(funcionarioService.findById(Long.valueOf(jwt.getSubject()))).build();
    }

    @PATCH
    @Path("/alterar-senha")
    public Response alterarSenha(@Valid AlterarSenhaDTO dto) {
        //TODO validação
        if(funcionarioService.alterarSenha(dto, Long.valueOf(jwt.getSubject()))){
            return Response.noContent().build();
        }
        return Response.serverError().build();
    }

}
