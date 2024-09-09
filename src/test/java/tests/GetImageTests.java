package tests;

import static org.junit.jupiter.api.Assertions.*;

import com.google.inject.Inject;
import extensions.ApiExtension;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import services.ImageApi;

@ExtendWith(ApiExtension.class)
public class GetImageTests {
  
  @Inject
  private ImageApi imageApi;
  
  @Test
  public void getImageTest() {
    Response actualResponse = imageApi.getImage("happy kitty").extract().response();
    String contentType = actualResponse.getHeader("Content-Type");
    byte[] imageBytes = actualResponse.getBody().asByteArray();
    
    assertAll(
        () -> assertEquals(200, actualResponse.getStatusCode(), "Status code should be 200"),
        () -> assertTrue(contentType.startsWith("image/"), "Content-Type should start with 'image/'"),
        () -> assertTrue(imageBytes.length > 0, "Image should not be empty"),
        () -> assertTrue(imageBytes.length > 1000, "Image size should be greater than 1000 bytes")
    );
  }
  
  @Test
  public void getImageError500Test() {
    Response actualResponse = imageApi.getImage("").extract().response();
    
    assertAll(
        () -> assertEquals(500, actualResponse.getStatusCode(), "Status code should be 500"),
        () -> assertEquals("Internal Server Error", actualResponse.jsonPath().getString("error")),
        () -> assertEquals("/ai/get-image", actualResponse.jsonPath().getString("path"))
    );
  }
  
}
