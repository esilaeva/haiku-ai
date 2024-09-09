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
      summary = "Хайку",
      description = "Позволяет получить произвольную хайку"
  )
  @GetMapping("/get-haiku")
  public ResponseEntity<String> generateHaiku() {
    return ResponseEntity.ok(chatService.generateAiHaiku());
  }
  
  @Operation(
      summary = "Стихи",
      description = "Позволяет получить стихотворение на заданный жанр и тему"
  )
  @GetMapping("/get-poetry")
  public ResponseEntity<PoetryDto> generatePoetry(@RequestParam("genre") String genre,
                                                  @RequestParam("theme") String theme) {
    
    return ResponseEntity.ok(chatService.generatePoetryByGenreAndTheme(genre, theme));
  }
  
  @Operation(
      summary = "Путеводитель",
      description = "Позволяет получить путеводитель по городу и интересам"
  )
  @GetMapping("/city-guide")
  public ResponseEntity<String> generateCityGuide(@RequestParam("city") String city,
                                                  @RequestParam("interest") String interest) {
    
    return ResponseEntity.ok(chatService.generateCityGuide(city, interest));
  }
  
  @Operation(
      summary = "Картинки",
      description = "Позволяет получить произвольную картинку на заданную тему"
  )
  @GetMapping("/get-image")
  public void generateImage(HttpServletResponse httpServletResponse,
                            @RequestParam("prompt") String prompt) throws IOException {
    
    ImageResponse imageResponse = imageService.generateImage(prompt);
    
    String imageUrl = imageResponse.getResult().getOutput().getUrl();
    httpServletResponse.sendRedirect(imageUrl);
  }
  
}
