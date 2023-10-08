package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.CupomDTO;
import br.com.unitins.a1.dto.CupomResponseDTO;
import br.com.unitins.a1.service.CupomService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cupom")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CupomResource {
    @Inject
    CupomService service;

    @POST
    public Response create(@Valid CupomDTO dto){
        return Response.status(Response.Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(CupomDTO dto, @PathParam("id") Long id){
        service.update(dto, id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok().entity(service.findById(id)).build();
    }

    @GET
    @Path("/busca/{codigo}")
    public Response findByCodigo(@PathParam("codigo") String codigo){
        CupomResponseDTO cupom = service.findByCodigo(codigo);
        if(cupom != null) return Response.ok().entity(cupom).build();
        else return Response.status(404).build();
    }
}
