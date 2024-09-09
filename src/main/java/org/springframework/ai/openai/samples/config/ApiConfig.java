package org.springframework.ai.openai.samples.config;

import org.aeonbits.owner.Config;
import org.springframework.stereotype.Component;

@ApiConfig.Sources({
    "classpath:application.properties"
})
@Component
public interface ApiConfig extends Config {
  
  @Key("baseURI")
  String getBaseUrl();
  
  @Key("basePath")
  String getBasePath();
  
}
