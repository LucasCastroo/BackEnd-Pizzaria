package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.*;
import br.com.unitins.a1.model.NivelAcesso;
import br.com.unitins.a1.model.Pizza;
import br.com.unitins.a1.model.TamanhoPizza;
import br.com.unitins.a1.service.ItemService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ItemResourceTest {

    @Inject
    ItemService itemService;

    @Test
    void createPizza() {
        PizzaDTO dto = new PizzaDTO(
                "Pizza de Calabresa",
                "Calabresa, Cheddar e Ovo",
                40.00,
                600,
                TamanhoPizza.MEDIA,
                "Calabresa, Cheddar e Ovo",
                25
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/item/pizza")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("Pizza de Calabresa"),
                        "descricao", is("Calabresa, Cheddar e Ovo"),
                        "preco", is(40.00f),
                        "kCal", is(600),
                        "tamanhoPizza", is(TamanhoPizza.MEDIA.name()),
                        "tempoDePreparo", is(25)
                );
    }

    @Test
    void createBebida() {
        BebidaDTO dto = new BebidaDTO(
                "Coca-Cola",
                "Bebida Gelada",
                4.0,
                100,
                350
        );

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/item/bebida")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("Coca-Cola"),
                        "descricao", is("Bebida Gelada"),
                        "preco", is(4.0f),
                        "kCal", is(100),
                        "ml", is(350)
                );
    }

    @Test
    void updatePizza() {
        PizzaDTO dto = new PizzaDTO(
                "Pizza de Calabresa",
                "Calabresa, Cheddar e Ovo",
                40.00,
                600,
                TamanhoPizza.MEDIA,
                "Calabresa, Cheddar e Ovo",
                25
        );
    }

    @Test
    void updateBebida() {
    }

    @Test
    void testUpdatePizza() {
    }

    @Test
    void testUpdateBebida() {
    }

    @Test
    void findPizza() {
    }

    @Test
    void findBebida() {
    }
}