package extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.GuiceModule;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.MockitoAnnotations;

public class ApiExtension implements BeforeEachCallback {
  
  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    Injector injector = Guice.createInjector(new GuiceModule());
    
    MockitoAnnotations.openMocks(extensionContext.getRequiredTestInstance());
    
    Object testInstance = extensionContext.getRequiredTestInstance();
    injector.injectMembers(testInstance);
    
  }
  
}
