import Serialisation.SingleUser;
import Serialisation.User;
import config.Endpoints;
import config.TestConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.internal.matcher.xml.XmlXsdMatcher.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestUserSerialisation extends TestConfig {

    @Test
    @DisplayName("Create Serialisation.User with serialisation by JSON")
    public void createUserByJSON() {

        User user = new User("David", "Mechanic");

        given()
                .body(user.toString())
                .when()
                .post(Endpoints.CREATE_USER)
                .then();

    }

    @Test
    @DisplayName("Test users XML schema")
    public void checkUsersXMLSchema() {

        given()
                .pathParam("userId", 2)
                .when()
                .get(Endpoints.GET_SINGLE_USER_BY_ID)
                .then()
                .body(matchesXsdInClasspath("UsersXSDSchema.xsd"));

    }


    @Test
    @DisplayName("Test users JSON schema")
    public void checkUsersJSONSchema() {

        given()
                .queryParam("page", 2)
                .when()
                .get(Endpoints.GET_USERS_BY_PAGE)
                .then()
                .body(matchesJsonSchemaInClasspath("UsersJsonScheme.json"));

    }

    @Test
    @DisplayName("Convert JSON response to POJO")
    public void convertJSONToPOJO() {

        Response response = given()
                .when()
                .get(Endpoints.GET_LIST_OF_USERS_INFO);

        SingleUser user = response.getBody().as(SingleUser.class);
        System.out.println(user);

    }

}
