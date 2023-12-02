package br.unitins.tp1.pizzaria.resource;

import br.unitins.tp1.pizzaria.dto.*;
import br.unitins.tp1.pizzaria.model.Cliente;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.ClaimType;
import io.quarkus.test.security.jwt.JwtSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


@TestSecurity(user = "cliente1@email.com", roles = {Cliente.ROLE})
@JwtSecurity(
        claims = {
                @Claim(key = "sub", value = "1", type = ClaimType.LONG)
        }
)
@QuarkusTest
class UsuarioLogadoResourceTest {

    @Test
    void minhaConta() {
        given()
                .when()
                .get("/minha-conta")
                .then()
                .statusCode(200)
                .body(
                        "email", is("cliente1@email.com")
                );
    }

    @Test
    void alterarSenha() {
        AlterarSenhaDTO dto = new AlterarSenhaDTO("cliente1", "senha123");
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(dto)
                .patch("/minha-conta/alterar-senha")
                .then()
                .statusCode(204);
    }

    @Test
    void alterarNome() {
        NomeDTO dto = new NomeDTO("José");
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(dto)
                .patch("/minha-conta/alterar-nome")
                .then()
                .statusCode(200)
                .body(
                        "nome", is("José")
                );
    }

    @Test
    void alterarEmail() {
        EmailDTO dto = new EmailDTO("jose@email.com");
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(dto)
                .patch("/minha-conta/alterar-email")
                .then()
                .statusCode(200)
                .body(
                        "email", is("jose@email.com")
                );
    }

    @Test
    void alterarCpf() {
        CPFDTO dto = new CPFDTO("834.488.060-21");
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(dto)
                .patch("/minha-conta/alterar-cpf")
                .then()
                .statusCode(200)
                .body(
                        "cpf", is("834.488.060-21")
                );
    }

    @Test
    void alterarNascimento() {
        NascimentoDTO dto = new NascimentoDTO(LocalDate.of(2000,3,21));
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(dto)
                .patch("/minha-conta/alterar-nascimento")
                .then()
                .statusCode(200)
                .body(
                        "nascimento", is(LocalDate.of(2000, 3, 21).format(DateTimeFormatter.ISO_DATE))
                );
    }

    @Test
    void alterarTelefone() {
        TelefoneDTO dto = new TelefoneDTO("63988237165");
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(dto)
                .patch("/minha-conta/alterar-telefone")
                .then()
                .statusCode(200)
                .body(
                        "telefone", is("63988237165")
                );
    }
}