package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.*;
import br.com.unitins.a1.model.Bebida;
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
import static org.hamcrest.MatcherAssert.assertThat;
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
                40.0,
                600,
                TamanhoPizza.MEDIA,
                "Calabresa, Cheddar e Ovo",
                25
        );

        Pizza pizzaTest = itemService.createPizza(dto);
        Long id = pizzaTest.getId();

        PizzaDTO dtoUpdate = new PizzaDTO(
                "Pizza de Calabresa",
                "Calabresa, Cheddar, Ovo e Azeitona",
                45.0,
                600,
                TamanhoPizza.MEDIA,
                "Calabresa, Cheddar, Ovo e Azeitona",
                25
        );

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/item/pizza/"+ id)
                .then()
                .statusCode(202);

        Pizza piz = itemService.findPizza(id);
        assertThat(piz.getNome(), is("Pizza de Calabresa"));
        assertThat(piz.getDescricao(), is("Calabresa, Cheddar, Ovo e Azeitona"));
        assertThat(piz.getPreco(), is(45.0));
        assertThat(piz.getTamanhoPizza(), is(TamanhoPizza.MEDIA));
        assertThat(piz.getIngredientes(), is("Calabresa, Cheddar, Ovo e Azeitona"));
        assertThat(piz.getTempoDePreparo(), is(25));
    }

    @Test
    void updateBebida() {
        BebidaDTO dto = new BebidaDTO(
                "Coca-Cola",
                "Bebida Gelada",
                4.0,
                100,
                350
        );

        Bebida bebidaTest = itemService.createBebida(dto);
        Long id = bebidaTest.getId();

        BebidaDTO dtoUpdate = new BebidaDTO(
                "Coca-Cola",
                "Bebida Gelada",
                6.0,
                100,
                350
        );

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/item/bebida/"+ id)
                .then()
                .statusCode(202);

        Bebida beb = itemService.findBebida(id);
        assertThat(beb.getNome(), is("Coca-Cola"));
        assertThat(beb.getDescricao(), is("Bebida Gelada"));
        assertThat(beb.getPreco(), is(6.0));
        assertThat(beb.getkCal(), is(100));
        assertThat(beb.getMl(), is(350));
    }


    @Test
    void findPizza() {
        PizzaDTO dto = new PizzaDTO(
                "Pizza de Calabresa",
                "Calabresa, Cheddar e Ovo",
                40.0,
                600,
                TamanhoPizza.MEDIA,
                "Calabresa, Cheddar e Ovo",
                25
        );

        Pizza pizzaTest = itemService.createPizza(dto);

        given()
                .when().get("/item/pizza/" + pizzaTest.getId())
                .then()
                .statusCode(200);
    }

    @Test
    void findBebida() {
    }
}