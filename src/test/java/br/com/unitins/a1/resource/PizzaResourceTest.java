package br.com.unitins.a1.resource;

import br.com.unitins.a1.dto.PizzaDTO;
import br.com.unitins.a1.dto.PizzaResponseDTO;
import br.com.unitins.a1.model.Pizza;
import br.com.unitins.a1.model.TamanhoPizza;
import br.com.unitins.a1.service.PizzaServiceImpl;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class PizzaResourceTest {

    @Inject
    PizzaServiceImpl pizzaService;

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
                .header(TestUtils.authFuncionario)
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

        PizzaResponseDTO pizzaTest = pizzaService.create(dto);
        Long id = pizzaService.findById();

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
                .header(TestUtils.authFuncionario)
                .body(dtoUpdate)
                .when().put("/item/pizza/"+ id)
                .then()
                .statusCode(202);

        PizzaResponseDTO piz = pizzaService.findById(id);
        assertThat(piz.getNome(), is("Pizza de Calabresa"));
        assertThat(piz.getDescricao(), is("Calabresa, Cheddar, Ovo e Azeitona"));
        assertThat(piz.getPreco(), is(45.0));
        assertThat(piz.getTamanhoPizza(), is(TamanhoPizza.MEDIA));
        assertThat(piz.getIngredientes(), is("Calabresa, Cheddar, Ovo e Azeitona"));
        assertThat(piz.getTempoDePreparo(), is(25));
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
                .when()
                .header(TestUtils.authFuncionario)
                .get("/item/pizza/" + pizzaTest.getId())
                .then()
                .statusCode(200);
    }
}
