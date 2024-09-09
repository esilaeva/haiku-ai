package org.springframework.ai.openai.samples.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    //The core for image-based interactions.
    private final ImageModel imageClient;

    @Override
    public ImageResponse generateImage(String prompt) {
        ImageOptions imageOptions = ImageOptionsBuilder.builder()
                .withN(1)
                .withHeight(1024)
                .withWidth(1024)
                .build();

        return imageClient.call(new ImagePrompt(prompt, imageOptions));
    }
    
}
