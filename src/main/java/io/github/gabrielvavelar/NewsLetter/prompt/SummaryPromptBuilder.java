package io.github.gabrielvavelar.NewsLetter.prompt;

import io.github.gabrielvavelar.NewsLetter.model.NewsArticle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SummaryPromptBuilder {

    public String build(List<NewsArticle> articles) {
        return basePrompt().formatted(formatArticles(articles));
    }

    public String basePrompt() {
        return """
                Você é um editor de newsletter experiente;
                Abaixo estão os artigos coletados hoje:
                
                %s
                
                Com base neles, escreva um resumo atraente para os inscritos.
                """;
    }

    public String formatArticles(List<NewsArticle> articles) {
        StringBuilder sb = new StringBuilder();
        for (NewsArticle article : articles) {
            sb.append(article.getTitle()).append("\n");
            sb.append(article.getDescription()).append("\n");
        }
        return sb.toString();
    }
}
