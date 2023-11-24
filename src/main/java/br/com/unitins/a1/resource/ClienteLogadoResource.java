package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.AlterarSenhaDTO;
import br.com.unitins.a1.form.ItemImageForm;
import br.com.unitins.a1.model.Cliente;
import br.com.unitins.a1.service.ClienteService;
import br.com.unitins.a1.service.HashService;
import br.com.unitins.a1.service.ItemFileService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.IOException;

@Path("/minha-conta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed(Cliente.ROLE)
public class ClienteLogadoResource {
    @Inject
    JsonWebToken jwt;

    @Inject
    ClienteService clienteService;

    @Inject
    HashService hashService;

    @Inject
    ItemFileService fileService;

    @GET
    public Response minhaConta() {
        return Response.ok(clienteService.findById(Long.valueOf(jwt.getSubject()))).build();
    }

    @PATCH
    @Path("/alterar-senha")
    public Response alterarSenha(@Valid AlterarSenhaDTO dto) {
        //TODO validação
        if(clienteService.alterarSenha(dto, Long.valueOf(jwt.getSubject()))){
            return Response.noContent().build();
        }
        return Response.serverError().build();
    }

    @PATCH
    @Path("/upload/imagem")
    @RolesAllowed({ "User", "Admin" })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ItemImageForm form) {
        String nomeImagem;
        try {
            nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
        } catch (IOException e) {
            e.printStackTrace();
            Error error = new Error("409", e.getMessage());
            return Response.status(Response.Status.CONFLICT).entity(error).build();
        }

        String login = jwt.getSubject();
        UsuarioResponseDTO usuarioDTO = usuarioService.findByLogin(login);
        usuarioDTO = usuarioService.updateNomeImagem(usuarioDTO.id(), nomeImagem);

        return Response.ok(usuarioDTO).build();
    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.obter(nomeImagem));
        response.header("Content-Disposition", "attachment;filename="+nomeImagem);
        return response.build();
    }
}
