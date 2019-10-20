import config.Endpoints;
import config.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserGeneral extends TestConfig {

    @Test
    @DisplayName("Get List Of Users use query parameter")
    @Tag("DEV")
    public void getUsersList() {
        given()
                .queryParam("page", 2)
                .when()
                .get(Endpoints.GET_USERS_BY_PAGE)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Check ID user in List Of Users use query parameter")
    @Tag("DEV")
    public void checkIdInUsersList() {
        given()
                .queryParam("page", 2)
                .when()
                .get(Endpoints.GET_USERS_BY_PAGE)
                .then()
                .body("data.id[0]", equalTo(7));
    }

    @Test
    @DisplayName("Check UserName in List Of Users use query parameter")
    @Tag("DEV")
    public void checkUserNameInUsersList() {
        given()
                .queryParam("page", 2)
                .when()
                .get(Endpoints.GET_USERS_BY_PAGE)
                .then()
                .body("data.first_name[0]", equalTo("Michael"));
    }

    @Test
    @DisplayName("Check email Single Serialisation.User")
    @Tag("DEV")
    public void checkEmailSingleUser() {
        given()
                .pathParam("userId", 2)
                .when()
                .get(Endpoints.GET_SINGLE_USER_BY_ID)
                .then()
                .body("data.email", equalTo("janet.weaver@reqres.in"));
    }

    @Test
    @DisplayName("Check Last Name Single Serialisation.User")
    @Tag("DEV")
    public void checkLastNameSingleUser() {
        given()
                .pathParam("userId", 3)
                .when()
                .get(Endpoints.GET_SINGLE_USER_BY_ID)
                .then()
                .body("data.last_name", equalTo("Wong"));
    }

    @Test
    @DisplayName("Single Serialisation.User not Found")
    @Tag("DEV")
    public void getSingleUserNotFound() {
        given()
                .pathParam("userId", 23)
                .when()
                .get(Endpoints.GET_SINGLE_USER_NOT_FOUND_BY_ID)
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("Get list info about users")
    @Tag("DEV")
    public void getListInfoByUsers() {
        given()
                .when()
                .get(Endpoints.GET_LIST_OF_USERS_INFO)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Check total count users")
    @Tag("DEV")
    public void checkTotalUsers() {
        given()
                .when()
                .get(Endpoints.GET_LIST_OF_USERS_INFO)
                .then()
                .body("total", equalTo(12));
    }

    @Test
    @DisplayName("Check total pages count")
    @Tag("DEV")
    public void checkTotalPages() {
        given()
                .when()
                .get(Endpoints.GET_LIST_OF_USERS_INFO)
                .then()
                .body("total_pages", equalTo(2));
    }

    @Test
    @DisplayName("Check to exist name")
    @Tag("DEV")
    public void checkExistName() {
        given()
                .when()
                .get(Endpoints.GET_LIST_OF_USERS_INFO)
                .then()
                .body("data.name", hasItem("cerulean"));
    }

    @Test
    @DisplayName("Check to exist green color")
    @Tag("DEV")
    public void checkExistGreenColor() {
        given()
                .when()
                .get(Endpoints.GET_LIST_OF_USERS_INFO)
                .then()
                .body("data.color", hasItem("#98B2D1"));
    }

    @Test
    @DisplayName("Check to exist year")
    @Tag("DEV")
    public void checkAllYear() {
        given()
                .when()
                .get(Endpoints.GET_LIST_OF_USERS_INFO)
                .then()
                .body("data.year", hasItem(2004));
    }

    @Test
    @DisplayName("Check to exist years")
    @Tag("DEV")
    public void checkAllYears() {
        given()
                .when()
                .get(Endpoints.GET_LIST_OF_USERS_INFO)
                .then()
                .body("data.year", hasItems(2000, 2001, 2002, 2003, 2004, 2005));
    }

    @Test
    @DisplayName("Check to exist years with extract response")
    @Tag("DEV")
    public void checkAllYearsWithExtractResponse() {
        Response response = given()
                .when()
                .get(Endpoints.GET_LIST_OF_USERS_INFO)
                .then()
                .extract().response();

        List<Integer> years = response.path("data.year");
        assertEquals(years.size(), 6);
    }

    @Test
    @DisplayName("Check to exist size all year")
    @Tag("DEV")
    public void checkSizeAllYears() {
       given()
                .when()
                .get(Endpoints.GET_LIST_OF_USERS_INFO)
                .then()
                .body("data.year", hasSize(6));
    }

    @Test
    @Tag("SMOKE")
    @DisplayName("Get single info about user")

    public void getSingleInfoByUser() {
        given()
                .pathParam("userId", 2)
                .when()
                .get(Endpoints.GET_USER_INFO_BY_ID)
                .then()
                .statusCode(200);
    }

    @Test
    @Tag("SMOKE")
    @DisplayName("Check panton value user")

    public void checkPantoneValueUser() {
        given()
                .pathParam("userId", 2)
                .when()
                .get(Endpoints.GET_USER_INFO_BY_ID)
                .then()
                .body("data.pantone_value", equalTo("17-2031"));
    }

    @Test
    @Tag("SMOKE")
    @DisplayName("Create Serialisation.User")

    public void CreateUser() {
        String bodyJSON = "{\n" +
                "    \"name\": \"andrew\",\n" +
                "    \"job\": \"QA\"\n" +
                "}";
        given()
                .body(bodyJSON).
                when()
                .post(Endpoints.CREATE_USER).
                then()
                .statusCode(201);
    }

    @Test
    @Tag("SMOKE")
    @DisplayName("Create Serialisation.User")

    public void UpdateUserInfo() {

        given()
                .pathParam("userId", 631)
                .when()
                .delete(Endpoints.DELETE_USER_BY_ID).
                then()
                .statusCode(204);
    }


    @Test
    @Tag("SMOKE")
    @DisplayName("Extract headers from response")

    public void extractHeaders() {
        Response response = given()
                .pathParam("userId", 2)
                .when()
                .get(Endpoints.GET_USER_INFO_BY_ID)
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        Headers headers = response.getHeaders();
        System.out.println(headers);
    }

    @Test
    @DisplayName("Extract concrete header from response")

    public void extractConcreteHeader() {
        Response response = given()
                .pathParam("userId", 2)
                .when()
                .get(Endpoints.GET_USER_INFO_BY_ID)
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        String contentType = response.getHeader("Content-Type");
        System.out.println(contentType);
    }

    @Test
    @DisplayName("Extract name from response use JSON path")

    public void extractNameUseJsonPath() {
        String name = given()
                .pathParam("userId", 2)
                .when()
                .get(Endpoints.GET_USER_INFO_BY_ID)
                .jsonPath()
                .getString("data.name");

        System.out.println(name);
    }

    @Test
    @DisplayName("Extract all names from response use JSON path")

    public void extractAllNamesUseJsonPath() {
        Response response= given()
                .when()
                .get(Endpoints.GET_LIST_OF_USERS_INFO)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        List<String> names = response.path("data.name");
        names.stream().sorted().forEach(System.out::println);
        assertEquals(names.size(), 6);
    }

    @Test
    @DisplayName("Test response time")
    public void testResponseTime() {

        long responseTime = given()
                .pathParam("userId", 2)
                .when()
                .get(Endpoints.GET_SINGLE_USER_BY_ID)
                .time();

        System.out.println(responseTime + "ms");

    }

    @Test
    @DisplayName("Check response time")
    public void checkResponseTime() {

        given()
                .pathParam("userId", 2)
                .when()
                .get(Endpoints.GET_SINGLE_USER_BY_ID)
                .then()
                .time(lessThan(1300L));

    }

}
