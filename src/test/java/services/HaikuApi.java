package services;

import static io.restassured.RestAssured.given;

import annotations.UrlPrefix;
import com.google.inject.Inject;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

@UrlPrefix("/get-haiku")
public class HaikuApi extends ServiceApiAbstract {
  
  private RequestSpecification spec;
  
  @Inject
  public HaikuApi() {
    spec = given()
        .baseUri(getBaseUrl())
        .basePath(getBasePath())
        .contentType(ContentType.JSON);
  }
  
  public ValidatableResponse getHaiku() {
    
    return given(spec)
        .log().all()
        .when()
        .get(getUrlPrefix())
        .then()
        .log().all();
  }
  
  public ValidatableResponse getHaikuNotFound() {
    
    return given(spec)
        .basePath(getUrlPrefix())
        .log().all()
        .when()
        .get()
        .then()
        .log().all();
  }
  
}
