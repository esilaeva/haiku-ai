package tests;

import static org.junit.jupiter.api.Assertions.*;

import com.google.inject.Inject;
import extensions.ApiExtension;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import services.CityGuideApi;

@ExtendWith(ApiExtension.class)
public class CityGuideTests {
  
  @Inject
  private CityGuideApi cityGuideApi;
  
  @Test
  public void getCityGuideTest() {
    Response actualResponse = cityGuideApi.getCityGuide("Moscow", "Shopping").extract().response();
    
    assertAll("cityGuide",
        () -> assertEquals(200, actualResponse.getStatusCode(), "Status code should be 200"),
        () -> assertTrue(actualResponse.getBody().asString().contains("Visit"))
    );
  }
  
}
