package it.eng.parer.classierrore.runner.rest;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.security.TestSecurity;
import static io.restassured.RestAssured.given;
import it.eng.parer.classierrore.Profiles;
import static it.eng.parer.classierrore.beans.utils.Costants.URL_GET_INFOS;

@QuarkusTest
@TestProfile(Profiles.EndToEnd.class)
class InfosEndpointTest {

    @Test
    @TestSecurity(user = "test_microservizi", roles = {
	    "admin" })
    void successAny() {
	given().when().get(URL_GET_INFOS).then().statusCode(200).body("$", hasKey("git"))
		.body("git.git.build.version", not(empty()));
    }

    @Test
    @TestSecurity(authorizationEnabled = true)
    void authRequest() {
	given().when().get(URL_GET_INFOS).then().statusCode(401);
    }

    @Test
    @TestSecurity(user = "fakeuser", roles = {
	    "not_admin" })
    void notAuthRequest() {
	given().when().get(URL_GET_INFOS).then().statusCode(403);
    }
}
