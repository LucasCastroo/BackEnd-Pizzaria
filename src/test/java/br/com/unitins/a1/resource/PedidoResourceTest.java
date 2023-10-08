package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.*;
import br.com.unitins.a1.model.FormaPagamento;
import br.com.unitins.a1.model.Pizza;
import br.com.unitins.a1.model.TamanhoPizza;
import br.com.unitins.a1.service.ClienteService;
import br.com.unitins.a1.service.ItemService;
import br.com.unitins.a1.service.PedidoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

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

        Pizza pizza = itemService.createPizza(pizzaDto);

        ClienteDTO clienteDTO = new ClienteDTO(
                "Janio Junior",
                "111.111.111-11",
                "janio@gmail.com",
                "111111",
                "(11) 11111-1111",
                "1994-01-01",
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
    void findByClienteId() {
    }
}