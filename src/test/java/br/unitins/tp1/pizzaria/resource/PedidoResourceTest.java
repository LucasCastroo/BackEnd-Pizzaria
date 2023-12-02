package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.*;
import br.unitins.tp1.pizzaria.model.*;
import br.unitins.tp1.pizzaria.service.ClienteService;
import br.unitins.tp1.pizzaria.service.PedidoService;
import br.unitins.tp1.pizzaria.service.PizzaServiceImpl;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.ClaimType;
import io.quarkus.test.security.jwt.JwtSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestSecurity(user = "cliente1@email.com", roles = {Cliente.ROLE})
@JwtSecurity(
        claims = {
                @Claim(key = "sub", value = "1", type = ClaimType.LONG)
        }
)
class PedidoResourceTest {
    @Inject
    PedidoService service;

    @Inject
    ClienteService clienteService;

    @Inject
    PizzaServiceImpl itemService;

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

        ItemResponseDTO<Pizza> pizza = itemService.create(pizzaDto);

        PedidoDTO dto = new PedidoDTO(
                List.of(new ItemPedidoDTO(1, pizza.getId(), TipoItem.PIZZA)),
                "CUPOM01",
                1L,
                FormaPagamento.PIX
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .post("/pedido")
                .then()
                .statusCode(201)
                .body(
                        "cliente.id", is(1),
                        "cupom.codigo", is("CUPOM01"),
                        "items[0].item.nome", is("Pizza de Calabresa"),
                        "items[0].item.id", is(pizza.getId().intValue())
                );

    }

    @Test
    void update() {
        PizzaDTO pizzaDto = new PizzaDTO(
                "Pizza de Calabresa",
                "Calabresa, Cheddar e Ovo",
                40.00,
                600,
                TamanhoPizza.MEDIA,
                "Calabresa, Cheddar e Ovo",
                25
        );

        ItemResponseDTO<Pizza> pizza = itemService.create(pizzaDto);

        PedidoDTO dto = new PedidoDTO(
                List.of(new ItemPedidoDTO(1, pizza.getId(), TipoItem.PIZZA)),
                "CUPOM01",
                1L,
                FormaPagamento.PIX
        );

        PedidoResponseDTO pedido = service.create(dto, 1L);

        PedidoDTO updateDto = new PedidoDTO(
                null,
                null,
                null,
                FormaPagamento.DINHEIRO
        );

        given()
                .contentType(ContentType.JSON)
                .body(updateDto)
                .put("/pedido/" + pedido.id())
                .then()
                .statusCode(202)
                .body(
                        "formaPagamento", is("DINHEIRO")
                );
    }

    @Test
    @TestSecurity(user = "funcionario1@email.com", roles = {Funcionario.ROLE, NivelAcesso.Role.ADMIN})
    @JwtSecurity(
            claims = {
                    @Claim(key = "sub", value = "1", type = ClaimType.LONG)
            }
    )
    void updateStatus() {
        PizzaDTO pizzaDto = new PizzaDTO(
                "Pizza de Calabresa",
                "Calabresa, Cheddar e Ovo",
                40.00,
                600,
                TamanhoPizza.MEDIA,
                "Calabresa, Cheddar e Ovo",
                25
        );

        ItemResponseDTO<Pizza> pizza = itemService.create(pizzaDto);

        PedidoDTO dto = new PedidoDTO(
                List.of(new ItemPedidoDTO(1, pizza.getId(), TipoItem.PIZZA)),
                "CUPOM01",
                1L,
                FormaPagamento.PIX
        );

        PedidoResponseDTO pedido = service.create(dto, 1L);

        StatusPedidoDTO statusPedidoDTO = new StatusPedidoDTO(Status.CANCELADO);

        given()
                .contentType(ContentType.JSON)
                .body(statusPedidoDTO)
                .patch("/pedido/" + pedido.id() + "/status")
                .then()
                .statusCode(202)
                .body(
                        "historicoStatus[0].status", is("CANCELADO")
                );
    }

    @Test
    @TestSecurity(user = "funcionario1@email.com", roles = {Funcionario.ROLE, NivelAcesso.Role.ADMIN})
    @JwtSecurity(
            claims = {
                    @Claim(key = "sub", value = "1", type = ClaimType.LONG)
            }
    )
    void delete() {
        PizzaDTO pizzaDto = new PizzaDTO(
                "Pizza de Calabresa",
                "Calabresa, Cheddar e Ovo",
                40.00,
                600,
                TamanhoPizza.MEDIA,
                "Calabresa, Cheddar e Ovo",
                25
        );

        ItemResponseDTO<Pizza> pizza = itemService.create(pizzaDto);

        PedidoDTO dto = new PedidoDTO(
                List.of(new ItemPedidoDTO(1, pizza.getId(), TipoItem.PIZZA)),
                "CUPOM01",
                1L,
                FormaPagamento.PIX
        );

        PedidoResponseDTO pedido = service.create(dto, 1L);

        StatusPedidoDTO statusPedidoDTO = new StatusPedidoDTO(Status.CANCELADO);

        given()
                .contentType(ContentType.JSON)
                .body(statusPedidoDTO)
                .delete("/pedido/" + pedido.id())
                .then()
                .statusCode(204);
    }

    @Test
    void findById() {
        PizzaDTO pizzaDto = new PizzaDTO(
                "Pizza de Calabresa",
                "Calabresa, Cheddar e Ovo",
                40.00,
                600,
                TamanhoPizza.MEDIA,
                "Calabresa, Cheddar e Ovo",
                25
        );

        ItemResponseDTO<Pizza> pizza = itemService.create(pizzaDto);

        PedidoDTO dto = new PedidoDTO(
                List.of(new ItemPedidoDTO(1, pizza.getId(), TipoItem.PIZZA)),
                "CUPOM01",
                1L,
                FormaPagamento.PIX
        );

        PedidoResponseDTO pedido = service.create(dto, 1L);

        given()
                .when()
                .get("/pedido/" + pedido.id())
                .then()
                .statusCode(200)
                .body(
                        "formaPagamento", is("PIX")
                );
    }


    @Test
    void findByClienteId() {
        given()
                .when()
                .get("/pedido")
                .then()
                .statusCode(200);
    }

    @Test
    void pagar() {
        given()
                .when()
                .patch("/pedido/1/pagar")
                .then()
                .statusCode(202);
    }
}