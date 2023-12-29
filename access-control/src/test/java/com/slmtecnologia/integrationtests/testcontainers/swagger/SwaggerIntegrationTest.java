package com.slmtecnologia.integrationtests.testcontainers.swagger;

import com.slmtecnologia.configs.TestConfigs;
import com.slmtecnologia.integrationtests.testcontainers.AbstractIntegrationTest;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {
    @Test
    void showDisplaySwaggerUiPage(){
        var content =  given()
                        .basePath("/swagger-ui/index.html")
                        .port(TestConfigs.SERVER_PORT)
                        .when()
                            .get()
                        .then()
                            .statusCode(200)
                        .extract()
                        .body().asString();
        assertTrue(content.contains("Swagger UI"));
    }
}
