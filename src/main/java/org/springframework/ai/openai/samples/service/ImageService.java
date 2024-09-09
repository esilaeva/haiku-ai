package org.springframework.ai.openai.samples.service;

import org.springframework.ai.image.ImageResponse;

public interface ImageService {
    public ImageResponse generateImage(String prompt);
}
