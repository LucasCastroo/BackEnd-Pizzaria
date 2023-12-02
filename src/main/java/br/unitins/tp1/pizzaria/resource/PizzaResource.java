package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.PizzaDTO;
import br.unitins.tp1.pizzaria.service.PizzaServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

@ApplicationScoped
@Path("/pizza")
@SecurityRequirement(name = "SecurityScheme") // sem isso o swagger não usa autorização
public class PizzaResource extends ItemResource<PizzaServiceImpl, PizzaDTO> {
}
