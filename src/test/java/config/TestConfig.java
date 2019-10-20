package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.Matchers.lessThan;

public class TestConfig {

    public static RequestSpecification peopleRequestSpec;
    public static RequestSpecification footballRequestSpec;
    public static RequestSpecification usersApiRequestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeAll
    public static void setup() {

//        RestAssured.proxy("localhost", 8081);

        peopleRequestSpec = new RequestSpecBuilder().
                setBaseUri("https://swapi.co").
                setBasePath("/api/").
                addHeader("content-Type", "application/json").
                addHeader("accept", "application/json").
                build();

        footballRequestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.football-data.org").
                setBasePath("/v2/").
                addHeader("X-Auth-Token", "1f29ccc5899b452b87c24a23c4258803").
                addHeader("X-Response-Control", "minified").
                build();

        usersApiRequestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in").
                setBasePath("/api/").
                addHeader("X-Auth-Token", "1f29ccc5899b452b87c24a23c4258803").
                addHeader("X-Response-Control", "minified").
                build();


        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .build();

        RestAssured.requestSpecification = usersApiRequestSpec;
    }

}
