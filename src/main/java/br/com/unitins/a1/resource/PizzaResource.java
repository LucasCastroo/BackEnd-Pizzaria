package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.PizzaDTO;
import br.com.unitins.a1.service.PizzaServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

@ApplicationScoped
@Path("/pizza")
@SecurityRequirement(name = "SecurityScheme")
public class PizzaResource extends ItemResource<PizzaServiceImpl, PizzaDTO> {
}
