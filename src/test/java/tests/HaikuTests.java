package tests;

import static org.junit.jupiter.api.Assertions.*;

import com.google.inject.Inject;
import extensions.ApiExtension;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import services.HaikuApi;

@ExtendWith(ApiExtension.class)
public class HaikuTests {
  
  @Inject
  private HaikuApi haikuApi;
  
  @Test
  public void getHaikuSuccessTest() {
    Response actualResponse = haikuApi.getHaiku().extract().response();
    
    assertAll("haikuSuccess",
        () -> assertEquals(200, actualResponse.getStatusCode(), "Status code should be 200"),
        () -> assertTrue(actualResponse.getBody().asString().contains("AI"), "Response should contain 'AI'")
    );
  }
  
  @Test
  public void getHaikuFailureTest() {
    Response actualResponse = haikuApi.getHaikuNotFound().extract().response();
    
    assertAll("haikuFailure",
        () -> assertEquals(404, actualResponse.getStatusCode(), "Status code should be 404 for failure"),
        () -> assertTrue(actualResponse.getBody().asString().contains("Not Found"), "Response body should contain 'Not Found'")
    );
  }
  
}
