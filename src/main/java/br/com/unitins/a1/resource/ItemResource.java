package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.BebidaDTO;
import br.com.unitins.a1.dto.PizzaDTO;
import br.com.unitins.a1.service.ItemService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/item")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {
    @Inject
    ItemService itemService;

    @POST
    @Path("/pizza/")
    public Response createPizza(PizzaDTO dto){
        return Response.status(Response.Status.CREATED).entity(itemService.createPizza(dto)).build();
    }
    @POST
    @Path("/bebida/")
    public Response createBebida(BebidaDTO dto){
        return Response.status(Response.Status.CREATED).entity(itemService.createBebida(dto)).build();
    }
    @PUT
    @Path("/pizza/{id}")
    public Response updatePizza(PizzaDTO dto, @PathParam("id") Long id){
        return Response.status(Response.Status.ACCEPTED).entity(itemService.updatePizza(dto, id)).build();
    }
    @PUT
    @Path("/bebida/{id}")
    public Response updateBebida(BebidaDTO dto, @PathParam("id") Long id){
        return Response.status(Response.Status.ACCEPTED).entity(itemService.updateBebida(dto, id)).build();
    }
    @DELETE
    @Path("/pizza/{id}")
    public Response updatePizza(@PathParam("id") Long id){
        itemService.deletePizza(id);
        return Response.noContent().build();
    }
    @DELETE
    @Path("/bebida/{id}")
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


}
