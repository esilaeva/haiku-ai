package org.springframework.ai.openai.samples.service;

import org.springframework.ai.openai.samples.dto.PoetryDto;

public interface ChatService {
    String generateAiHaiku();

    PoetryDto generatePoetryByGenreAndTheme(String genre, String theme);

    String generateCityGuide(String city, String interest);
}
