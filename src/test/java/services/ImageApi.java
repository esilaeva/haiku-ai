package services;

import static io.restassured.RestAssured.given;

import annotations.UrlPrefix;
import com.google.inject.Inject;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

@UrlPrefix("/get-image")
public class ImageApi extends ServiceApiAbstract {
  
  @Inject
  public ImageApi() {
    spec = given()
        .baseUri(getBaseUrl())
        .basePath(getBasePath())
        .contentType(ContentType.JSON);
  }
  
  public ValidatableResponse getImage(String prompt) {
    
    return given(spec)
        .queryParam("prompt", prompt)
        .log().all()
        .when()
        .get(getUrlPrefix())
        .then()
        .log().all();
  }
  
}
