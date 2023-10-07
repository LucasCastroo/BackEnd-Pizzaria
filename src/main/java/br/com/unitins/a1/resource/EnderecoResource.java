package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.EnderecoDTO;
import br.com.unitins.a1.dto.EnderecoResponseDTO;
import br.com.unitins.a1.service.EnderecoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {
    @Inject
    EnderecoService service;

    @POST
    public Response insert(@Valid EnderecoDTO dto, Long idCliente) {
        EnderecoResponseDTO retorno = service.insert(dto, idCliente);
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(EnderecoDTO dto, @PathParam("id") Long idEndereco) {
        service.update(dto, idEndereco);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/logradouro/{logradouro}")
    public Response findByNome(@PathParam("logradouro") String logradouro) {
        return Response.ok(service.findByLogradouro(logradouro)).build();
    }
}
