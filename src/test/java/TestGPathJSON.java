import config.Endpoints;
import config.TestConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class TestGPathJSON extends TestConfig {

    @Test
    @DisplayName("Extract Map of elements with find")
    public void extractMapWithFind() {

        Response response = get(Endpoints.GET_LIST_OF_USERS_INFO);

        Map<String, ?> allUserData = response.
                path("data.find { it.name == 'cerulean'}");

        System.out.println(allUserData);

    }

    @Test
    @DisplayName("Extract email by LastName value with find")
    public void extractEmailByLastNameWithFind() {

        Response response = given().
                queryParam("page", 2)
                .get(Endpoints.GET_USERS_BY_PAGE);

        String certainUser = response.
                path("data.find { it.last_name == 'Lawson'}.email");

        System.out.println(certainUser);

    }

    @Test
    @DisplayName("Extract list of names with FindAll and year > 2002")
    public void extractListOfNamesWithFindAll() {

        Response response = given().
                queryParam("page", 2)
                .get(Endpoints.GET_LIST_OF_USERS_INFO);

        List<String> listOfFirstNames = response.
                path("data.findAll { it.year > 2002}.name");

        listOfFirstNames.forEach(System.out::println);

    }

    @Test
    @DisplayName("Extract LastName for user with max id")
    public void extractLastNameForMaxId() {

        Response response = given().
                queryParam("page", 2)
                .get(Endpoints.GET_USERS_BY_PAGE);

        String certainUser = response.
                path("data.max { it.id }.last_name");

        System.out.println(certainUser);

    }

    @Test
    @DisplayName("Extract Users with concrete color and pantone_value")
    public void extractUsersWithFindAndFindAll() {

        String color = "#BF1932";
        String pantoneValue = "19-1664";

        Response response = given()
                .get(Endpoints.GET_LIST_OF_USERS_INFO);

        Map<String, ?> usersOf = response.path(
                "data.findAll { it.color == '%s' }.find { it.pantone_value == '%s' }",
                color, pantoneValue);

        System.out.println(usersOf);

    }




}
