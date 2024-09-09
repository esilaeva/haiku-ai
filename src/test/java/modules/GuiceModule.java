package modules;

import com.google.inject.AbstractModule;
import services.CityGuideApi;
import services.HaikuApi;
import services.PoetryApi;

public class GuiceModule extends AbstractModule {
  
  protected void configure() {
    bind(HaikuApi.class);
    bind(PoetryApi.class);
    bind(CityGuideApi.class);
  }
  
}
