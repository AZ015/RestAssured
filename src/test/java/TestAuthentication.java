import config.Endpoints;
import config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestAuthentication extends TestConfig {


    @Test
    @DisplayName("Test register user use email and password")
    public void registerTest() {

        String email = "ghnvc@mail.com";
        String password = "qwe123";

        given()
                .param("email", email)
                .param("password", password)
                .when()
                .post(Endpoints.LOGIN);

    }

    @Test
    @DisplayName("Test login with basic primitive authentication")
    public void loginTestWithBasicAuth() {

        String email = "regt@mail.com";
        String password = "123123";

        given()
                .auth()
                .basic(email, password)
                .when()
                .get(Endpoints.LOGIN)
        .then().log().all();

    }

    @Test
    @DisplayName("Test with OAuth-1")
    public void oauthTest() {

        given()
                .auth()
                .oauth("consumeKey", "consumeSecret", "consumeAccessToken", "secretToken")
                .when()
                .get(Endpoints.OAUTH_1_TEST)
                .then().log().all();

    }

    @Test
    @DisplayName("Test with OAuth-2")
    public void oauth2Test() {

        given()
                .auth()
                .oauth2("accessToken")
                .when()
                .get(Endpoints.OAUTH_1_TEST)
                .then().log().all();

    }
}
