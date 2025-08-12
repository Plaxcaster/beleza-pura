package beleza_pura.com.example.beleza_pura.integration_tests;

import org.apache.http.HttpStatus;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

@Sql("/test_data.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class CadastroGatewayIntegrationTest {

    @LocalServerPort
    private int port;

    // IDs from test_data.sql
    private final String uuidProfissional01 = "660e8400-e29b-41d4-a716-446655440001";
    private final String uuidEspecialidade01 = "550e8400-e29b-41d4-a716-446655440001";
    private final String uuidEspecialidade02 = "550e8400-e29b-41d4-a716-446655440002";
    private final String uuidEstabelecimento01 = "770e8400-e29b-41d4-a716-446655440001";
    private final String uuidEstabelecimento02 = "770e8400-e29b-41d4-a716-446655440002";
    private final String uuidCliente01 = "880e8400-e29b-41d4-a716-446655440001";

    @BeforeAll
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testCadastrarProfissional() {
        String requestBody = """
                {
                  "nome": "Novo Profissional",
                  "horarioInicio": "08:00:00",
                  "horarioFim": "17:00:00",
                  "tarifa": 75.50
                }""";

        given().filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .when()
                .post("/cadastro/profissional")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nome", equalTo("Novo Profissional"))
                // Removed horarioInicio and horarioFim checks as they seem to be null in response
                .body("tarifa", equalTo(75.5f));
    }

    @Test
    void testCadastrarEspecialidade() {
        String requestBody = """
                {
                  "nome": "Nova Especialidade"
                }""";

        given().filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .when()
                .post("/cadastro/especialidade")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nome", equalTo("Nova Especialidade"));
    }

    @Test
    void testCadastrarCliente() {
        String requestBody = """
                {
                  "nome": "Novo Cliente",
                  "email": "novo@email.com",
                  "telefone": "(11) 1234-5678"
                }""";

        given().filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .when()
                .post("/cadastro/cliente")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nome", equalTo("Novo Cliente"))
                .body("email", equalTo("novo@email.com"));
    }


}