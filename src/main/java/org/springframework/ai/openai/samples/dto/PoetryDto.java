package org.springframework.ai.openai.samples.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Poetry item")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PoetryDto {

  @Schema(description = "Genre")
  private String genre;
  @Schema(description = "Theme")
  private String theme;
  @Schema(description = "Format")
  private String format;

}