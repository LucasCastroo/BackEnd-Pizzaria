package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.PizzaDTO;
import br.unitins.tp1.pizzaria.dto.PizzaResponseDTO;
import br.unitins.tp1.pizzaria.model.Funcionario;
import br.unitins.tp1.pizzaria.model.NivelAcesso;
import br.unitins.tp1.pizzaria.model.TamanhoPizza;
import br.unitins.tp1.pizzaria.service.PizzaServiceImpl;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.ClaimType;
import io.quarkus.test.security.jwt.JwtSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
@TestSecurity(user = "funcionario1@email.com", roles = {Funcionario.ROLE, NivelAcesso.Role.ADMIN})
@JwtSecurity(
        claims = {
                @Claim(key = "sub", value = "1", type = ClaimType.LONG)
        }
)
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
                .body(dto)
                .when().post("/pizza/create")
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
                .when().put("/pizza/update/" + id)
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

        PizzaResponseDTO pizzaTest = pizzaService.create(dto);

        given()
                .when()
                .get("/pizza/" + pizzaTest.getId())
                .then()
                .statusCode(200);
    }
}
