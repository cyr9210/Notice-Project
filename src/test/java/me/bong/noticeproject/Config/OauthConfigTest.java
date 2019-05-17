package me.bong.noticeproject.Config;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "spring.config.location=classpath:/google.yml")
public class OauthConfigTest {

    @Before
    public void setUp(){
        RestAssured.baseURI="http://localhost";
        RestAssured.port=8080;
    }

    @Test
    public void googleOauth2(){
        given()
                .when()
                    .redirects().follow(false)
                    .get("/login/google")
                .then()
                    .statusCode(302)
                    .header("Location", containsString("https://accounts.google.com/o/oauth2/auth"));

    }

}