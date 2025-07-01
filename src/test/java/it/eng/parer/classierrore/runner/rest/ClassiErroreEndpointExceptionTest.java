package it.eng.parer.classierrore.runner.rest;

import static org.hamcrest.Matchers.hasKey;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.security.TestSecurity;
import static io.restassured.RestAssured.given;
import it.eng.parer.classierrore.Profiles;
import it.eng.parer.classierrore.beans.IClassiErroreService;
import it.eng.parer.classierrore.beans.exceptions.AppGenericRuntimeException;
import it.eng.parer.classierrore.beans.exceptions.ErrorCategory;
import static it.eng.parer.classierrore.beans.utils.Costants.COD_ERR_INTERNAL;
import static it.eng.parer.classierrore.beans.utils.Costants.URL_GET_CLASSIERRORE;

@QuarkusTest
@TestProfile(Profiles.EndToEnd.class)
class ClassiErroreEndpointExceptionTest {

    @InjectMock
    IClassiErroreService serviceMock;

    @Test
    @TestSecurity(user = "test_microservizi", roles = { "versatore" })
    void errorRequest() {
	when(serviceMock.listClassiErrorePerVarsFalliti(any(), any(), any(), any(), any()))
		.thenThrow(appGenericPersistenceException());
	given().when().queryParam("amb", "NO-amb").queryParam("ente", "NO-ente").queryParam("strut", "NO-strut")
		.get(URL_GET_CLASSIERRORE).then().statusCode(500).body("$", hasKey(COD_ERR_INTERNAL));
    }

    private AppGenericRuntimeException appGenericPersistenceException() {
	return AppGenericRuntimeException.builder().category(ErrorCategory.INTERNAL_ERROR)
		.message("Errore generico").build();
    }
}
