package services;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import annotations.UrlPrefix;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.springframework.ai.openai.samples.config.ApiConfig;

public class ServiceApiAbstract {
  
  protected static final ApiConfig config = ConfigFactory.create(ApiConfig.class);
  
  public RequestSpecification spec;
  
  public String getBaseUrl() {
    
    return config.getBaseUrl();
  }
  
  public String getBasePath() {
    
    return config.getBasePath();
  }
  
  public String getUrlPrefix() {
    UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
    if (urlAnnotation != null) {
      
      return urlAnnotation.value();
    }
    
    return EMPTY;
  }
  
}
