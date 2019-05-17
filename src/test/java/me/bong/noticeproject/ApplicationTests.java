package me.bong.noticeproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTests {

    @Test
    public void indexHtml() throws Exception {
        given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType("text/html")
                .body(containsString("로그인"));
    }



}
