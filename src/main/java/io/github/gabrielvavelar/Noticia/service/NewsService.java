package io.github.gabrielvavelar.Noticia.service;

import io.github.gabrielvavelar.Noticia.model.NewsArticle;
import io.github.gabrielvavelar.Noticia.model.Subscriber;
import io.github.gabrielvavelar.Noticia.service.scraper.NewsFetcher;
import io.github.gabrielvavelar.Noticia.service.sender.MessageSender;
import io.github.gabrielvavelar.Noticia.service.subscriber.SubscriberService;
import io.github.gabrielvavelar.Noticia.service.summary.SummaryService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.gabrielvavelar.Noticia.util.UnsubscribeLinkUtil.generateLink;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsFetcher fetcher;
    private final SummaryService summary;
    private final MessageSender sender;
    private final SubscriberService subscriberService;

    public List<NewsArticle> fetchNews() {
        var articles = fetcher.fetchLatestNews(20);
        String newsSummary = summary.generateSummary(articles);
        List<Subscriber> subscribers = subscriberService.getAllSubscribers();

        for (Subscriber subscriber : subscribers) {
            String content = newsSummary
                    + "\n\n Acesse o link para cancelar inscrição:"
                    + generateLink(subscriber.getUnsubscribeToken());

            sender.send(content, subscriber.getEmail());
        }

        return articles;
    }
}
