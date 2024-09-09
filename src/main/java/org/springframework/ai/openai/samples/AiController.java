package org.springframework.ai.openai.samples;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.samples.dto.PoetryDto;
import org.springframework.ai.openai.samples.service.ChatService;
import org.springframework.ai.openai.samples.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ai")
@RequiredArgsConstructor
public class AiController {
  
  private final ChatService chatService;
  private final ImageService imageService;
  
  
  @Operation(
      summary = "Haiku endpoint",
      description = "Allow to get funny haiku about AI"
  )
  @GetMapping("/get-haiku")
  public ResponseEntity<String> generateHaiku() {
    return ResponseEntity.ok(chatService.generateAiHaiku());
  }
  
  @Operation(
      summary = "Poetry",
      description = "Allows you to get a poem on a given genre and theme"
  )
  @GetMapping("/get-poetry")
  public ResponseEntity<PoetryDto> generatePoetry(@RequestParam("genre") String genre,
                                                  @RequestParam("theme") String theme) {
    
    return ResponseEntity.ok(chatService.generatePoetryByGenreAndTheme(genre, theme));
  }
  
  @Operation(
      summary = "City guide",
      description = "Little city guide on a given city and interest"
  )
  @GetMapping("/city-guide")
  public ResponseEntity<String> generateCityGuide(@RequestParam("city") String city,
                                                  @RequestParam("interest") String interest) {
    
    return ResponseEntity.ok(chatService.generateCityGuide(city, interest));
  }
  
  @Operation(
      summary = "Pics",
      description = "A random picture on a given topic"
  )
  @GetMapping("/get-image")
  public void generateImage(HttpServletResponse httpServletResponse,
                            @RequestParam("prompt") String prompt) throws IOException {
    
    ImageResponse imageResponse = imageService.generateImage(prompt);
    
    String imageUrl = imageResponse.getResult().getOutput().getUrl();
    httpServletResponse.sendRedirect(imageUrl);
  }
  
}
