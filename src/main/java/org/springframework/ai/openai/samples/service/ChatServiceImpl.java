package org.springframework.ai.openai.samples.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.samples.dto.PoetryDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    //The core for text-based interactions.
    private final ChatModel chatClient;

    @Override
    public String generateAiHaiku() {
        String promptString = """
                Write me haiku about artificial intelligence,
                haiku should start with the obligatory word AI
                """;
        return chatClient.call(promptString);
    }

    @Override
    public PoetryDto generatePoetryByGenreAndTheme(String genre, String theme) {

        BeanOutputConverter<PoetryDto> outputConverter = new BeanOutputConverter<>(PoetryDto.class);
        String promptString = """
                Write me {genre} poetry about {theme}
                {format}
                """;
        
        PromptTemplate promptTemplate = new PromptTemplate(promptString);
        promptTemplate.add("genre", genre);
        promptTemplate.add("theme", theme);
        promptTemplate.add("format", outputConverter.getFormat());
        
        ChatResponse response = chatClient.call(promptTemplate.create());
        
        return outputConverter.convert(response.getResult().getOutput().getContent());
    }

    @Override
    public String generateCityGuide(String city, String interest) {

        String promptString = """
                I am a tourist visiting the city of {city}.
                I am mostly interested in {interest}.
                Tell me tips on what to do there.
                """;

        PromptTemplate promptTemplate = new PromptTemplate(promptString);
        Map<String, Object> promptParams = Map.of("city", city, "interest", interest);
        Prompt prompt = promptTemplate.create(promptParams);

        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
    
}
