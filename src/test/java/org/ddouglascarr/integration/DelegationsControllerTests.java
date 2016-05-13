package org.ddouglascarr.integration;

import com.jayway.restassured.RestAssured;

import org.apache.http.HttpStatus;
import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.config.WebSecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(LiquidcanonApplication.class)
@WebAppConfiguration
@IntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {
                "classpath:sql/liquid-canon.sql",
                "classpath:sql/test-world.sql"
        }
)
public class DelegationsControllerTests
{

    @Value("${server.port}")
    int serverPort;

    @Before
    public void setup() throws Exception
    {
        System.out.println("Server Port: " + serverPort);
        RestAssured.port = serverPort;
    }

    @Test
    public void testTest() throws Exception
    {
        System.out.println("running test");
        given()
                .auth().basic("tender_hugle", "login")
        .when()
                .get("/units/1")
        .then()
                .statusCode(HttpStatus.SC_OK);
    }

}
