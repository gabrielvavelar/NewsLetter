package io.github.gabrielvavelar.NewsLetter.service.summary;

import com.google.genai.Client;
import io.github.gabrielvavelar.NewsLetter.config.GeminiProperties;
import io.github.gabrielvavelar.NewsLetter.model.NewsArticle;
import io.github.gabrielvavelar.NewsLetter.prompt.SummaryPromptBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.google.genai.types.GenerateContentResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryService {
    private final Client client;
    private final GeminiProperties properties;
    private final SummaryPromptBuilder promptBuilder;

    public String generateSummary(List<NewsArticle> articles) {
        GenerateContentResponse response =
                client.models.generateContent(
                        properties.model(),
                        promptBuilder.build(articles),
                        null);

        return response.text();
    }
}
