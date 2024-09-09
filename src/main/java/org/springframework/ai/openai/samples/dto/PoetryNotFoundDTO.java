package org.springframework.ai.openai.samples.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PoetryNotFoundDTO {
  
  @Schema(description = "request creation time")
  private String timestamp;
  @Schema(description = "statusCode")
  private int status;
  @Schema(description = "errorMessage")
  private String error;
  @Schema(description = "basePath")
  private String path;
  
}
