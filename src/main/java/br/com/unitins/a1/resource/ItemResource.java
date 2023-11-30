package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.BebidaDTO;
import br.com.unitins.a1.dto.PizzaDTO;
import br.com.unitins.a1.form.ItemImageForm;
import br.com.unitins.a1.model.NivelAcesso;
import br.com.unitins.a1.service.ItemFileService;
import br.com.unitins.a1.service.ItemService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.IOException;

@Path("/item")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {
    @Inject
    ItemService itemService;

    @Inject
    ItemFileService fileService;

    @POST
    @Path("/pizza/")
    @RolesAllowed({NivelAcesso.Role.GERENTE, NivelAcesso.Role.ADMIN})
    public Response createPizza(PizzaDTO dto){
        return Response.status(Response.Status.CREATED).entity(itemService.createPizza(dto)).build();
    }
    @POST
    @Path("/bebida/")
    @RolesAllowed({NivelAcesso.Role.GERENTE, NivelAcesso.Role.ADMIN})
    public Response createBebida(BebidaDTO dto){
        return Response.status(Response.Status.CREATED).entity(itemService.createBebida(dto)).build();
    }
    @PUT
    @Path("/pizza/{id}")
    @RolesAllowed({NivelAcesso.Role.GERENTE, NivelAcesso.Role.ADMIN})
    public Response updatePizza(PizzaDTO dto, @PathParam("id") Long id){
        return Response.status(Response.Status.ACCEPTED).entity(itemService.updatePizza(dto, id)).build();
    }
    @PUT
    @Path("/bebida/{id}")
    @RolesAllowed({NivelAcesso.Role.GERENTE, NivelAcesso.Role.ADMIN})
    public Response updateBebida(BebidaDTO dto, @PathParam("id") Long id){
        return Response.status(Response.Status.ACCEPTED).entity(itemService.updateBebida(dto, id)).build();
    }
    @DELETE
    @Path("/pizza/{id}")
    @RolesAllowed({NivelAcesso.Role.GERENTE, NivelAcesso.Role.ADMIN})
    public Response updatePizza(@PathParam("id") Long id){
        itemService.deletePizza(id);
        return Response.noContent().build();
    }
    @DELETE
    @Path("/bebida/{id}")
    @RolesAllowed({NivelAcesso.Role.GERENTE, NivelAcesso.Role.ADMIN})
    public Response updateBebida(@PathParam("id") Long id){
        itemService.deleteBebida(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/pizza/{id}")
    public Response findPizza(@PathParam("id") Long id){
        return Response.ok().entity(itemService.findPizza(id)).build();
    }

    @GET
    @Path("/bebida/{id}")
    public Response findBebida(@PathParam("id") Long id){
        return Response.ok().entity(itemService.findBebida(id)).build();
    }

//    @PATCH
//    @Path("/upload/imagem")
//    @RolesAllowed({ "User", "Admin" })
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response salvarImagem(@MultipartForm ItemImageForm form) {
//        String nomeImagem;
//        try {
//            nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
//        } catch (IOException e) {
//            e.printStackTrace();
//            Error error = new Error("409", e.getMessage());
//            return Response.status(Response.Status.CONFLICT).entity(error).build();
//        }
//
//        String login = jwt.getSubject();
//        UsuarioResponseDTO usuarioDTO = usuarioService.findByLogin(login);
//        usuarioDTO = usuarioService.updateNomeImagem(usuarioDTO.id(), nomeImagem);
//
//        return Response.ok(usuarioDTO).build();
//    }

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
