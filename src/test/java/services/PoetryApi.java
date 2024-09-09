package services;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import annotations.UrlPrefix;
import com.google.inject.Inject;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

@UrlPrefix("/get-poetry")
public class PoetryApi extends ServiceApiAbstract {
  
  @Inject
  public PoetryApi() {
    spec = given()
        .baseUri(getBaseUrl())
        .basePath(getBasePath())
        .contentType(ContentType.JSON);
  }
  
  public ValidatableResponse getPoetry(String genre, String theme, int statusCode) {
    
    return given(spec)
        .queryParam("genre", genre)
        .queryParam("theme", theme)
        .log().all()
        .when()
        .get(getUrlPrefix())
        .then()
        .log().all()
        .statusCode(statusCode);
  }
  
  public <T> T getPoetryBadRequest(String theme, int statusCode, String pathJsonSchema, Class<T> responseClass) {
    
    return given(spec)
        .queryParam(theme)
        .log().all()
        .when()
        .get(getUrlPrefix())
        .then()
        .log().all()
        .statusCode(statusCode)
        .body(matchesJsonSchemaInClasspath(pathJsonSchema))
        .extract()
        .body()
        .as(responseClass);
  }
  
}
