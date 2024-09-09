package services;

import static io.restassured.RestAssured.given;

import annotations.UrlPrefix;
import com.google.inject.Inject;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

@UrlPrefix("/city-guide")
public class CityGuideApi extends ServiceApiAbstract {
  
  @Inject
  public CityGuideApi() {
    spec = given()
        .baseUri(getBaseUrl())
        .basePath(getBasePath())
        .contentType(ContentType.JSON);
  }
  
  public ValidatableResponse getCityGuide(String city, String interest) {
    
    return given(spec)
        .queryParam("city", city)
        .queryParam("interest", interest)
        .log().all()
        .when()
        .get(getUrlPrefix())
        .then()
        .log().all();
  }
  
}
