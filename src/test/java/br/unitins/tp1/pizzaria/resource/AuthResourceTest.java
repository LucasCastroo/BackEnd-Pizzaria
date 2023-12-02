package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.LoginDTO;
import br.unitins.tp1.pizzaria.model.Cliente;
import br.unitins.tp1.pizzaria.model.Funcionario;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
class AuthResourceTest {
    @Inject
    JWTParser jwtParser;

    @Test
    void loginCliente() throws ParseException {
        LoginDTO dto = new LoginDTO("cliente1@email.com", "cliente1");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/auth/cliente")
                .then()
                .statusCode(200)
                .extract()
                .response();
        JsonWebToken jwt = jwtParser.parse(response.header("Authorization"));
        assertThat(jwt.getGroups(), CoreMatchers.hasItems(Cliente.ROLE));
        assertThat(jwt.getSubject(), is("1"));
        assertThat(jwt.getExpirationTime(), greaterThan(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));

    }

    @Test
    void loginFuncionario() throws ParseException {
        LoginDTO dto = new LoginDTO("funcionario1@email.com", "funcionario1");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/auth/funcionario")
                .then()
                .statusCode(200)
                .extract()
                .response();
        JsonWebToken jwt = jwtParser.parse(response.header("Authorization"));
        assertThat(jwt.getGroups(), CoreMatchers.hasItems(Funcionario.ROLE));
        assertThat(jwt.getSubject(), is("1"));
        assertThat(jwt.getExpirationTime(), greaterThan(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
    }
}