package tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.inject.Inject;
import extensions.ApiExtension;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.ai.openai.samples.dto.PoetryNotFoundDTO;
import org.springframework.ai.openai.samples.dto.PoetryDto;
import services.PoetryApi;

@ExtendWith(ApiExtension.class)
public class PoetryTests {
  
  @Inject
  private PoetryDto poetryDto;
  @Inject
  private PoetryApi poetryApi;
  
  @Test
  public void getGeneratedPoetrySuccessTest() {
    Response actualResponse = poetryApi.getPoetry("comedy", "morning", 200).extract().response();
    
    assertAll("poetry",
        () -> assertEquals(200, actualResponse.getStatusCode(), "Status code should be 200"),
        () -> assertEquals("comedy", actualResponse.jsonPath().getString("genre")),
        () -> assertEquals("morning", actualResponse.jsonPath().getString("theme"))
    );
  }
  
  @Test
  public void getGeneratedPoetryBadRequestTest() {
    PoetryNotFoundDTO actualResponse = poetryApi.getPoetryBadRequest("Morning", 400, "schema/poetry_badRequest.json", PoetryNotFoundDTO.class);
    
    assertAll("poetry",
        () -> assertEquals("Bad Request", actualResponse.getError()),
        () -> assertEquals("/ai/get-poetry", actualResponse.getPath())
    );
  }
  
}
