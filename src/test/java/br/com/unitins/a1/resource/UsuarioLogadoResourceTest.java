package br.com.unitins.a1.resource;

import br.com.unitins.a1.model.Cliente;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.ClaimType;
import io.quarkus.test.security.jwt.JwtSecurity;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void alterarNome() {
    }

    @Test
    void alterarEmail() {
    }

    @Test
    void alterarCpf() {
    }

    @Test
    void alterarNascimento() {
    }

    @Test
    void alterarTelefone() {
    }
}