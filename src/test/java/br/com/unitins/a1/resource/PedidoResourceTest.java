package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.*;
import br.com.unitins.a1.model.FormaPagamento;
import br.com.unitins.a1.model.Pizza;
import br.com.unitins.a1.model.TamanhoPizza;
import br.com.unitins.a1.service.ClienteService;
import br.com.unitins.a1.service.ItemService;
import br.com.unitins.a1.service.PedidoService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.ClaimType;
import io.quarkus.test.security.jwt.JwtSecurity;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
class PedidoResourceTest {
    @Inject
    PedidoService service;

    @Inject
    ClienteService clienteService;

    @Inject
    ItemService itemService;

    @Test
    void create() {
        PizzaDTO pizzaDto = new PizzaDTO(
                "Pizza de Calabresa",
                "Calabresa, Cheddar e Ovo",
                40.00,
                600,
                TamanhoPizza.MEDIA,
                "Calabresa, Cheddar e Ovo",
                25
        );

        Pizza pizza = itemService.create(pizzaDto);

        ClienteDTO clienteDTO = new ClienteDTO(
                "Janio Junior",
                "111.111.111-11",
                "janio@gmail.com",
                "111111",
                "(11) 11111-1111",
                LocalDate.of(1994,1,1),
                null
        );

        ClienteResponseDTO cliente = clienteService.insert(clienteDTO);

        PedidoDTO dto = new PedidoDTO(
                List.of(new ItemPedidoDTO(1, pizza.getId(), TipoItem.PIZZA)),
                "CUPOM01",
                1L,
                FormaPagamento.PIX
        );


    }

    @Test
    void update() {
    }

    @Test
    void updateStatus() {
    }

    @Test
    void delete() {

    }

    @Test
    void findById() {
    }

    @Test
    @TestSecurity(user = "cliente1", roles = {"CLIENTE"})
    @JwtSecurity(
            claims = {
                    @Claim(key = "sub", value = "1", type = ClaimType.LONG)
            }
    )
    void findByClienteId() {
        given()
                .when()
                .get("/pedido")
                .then()
                .statusCode(200);
    }
}