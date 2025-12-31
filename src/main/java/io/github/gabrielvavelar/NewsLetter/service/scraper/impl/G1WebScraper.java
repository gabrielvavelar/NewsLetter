package io.github.gabrielvavelar.NewsLetter.service.scraper.impl;

import io.github.gabrielvavelar.NewsLetter.exception.NewsScrapingException;
import io.github.gabrielvavelar.NewsLetter.model.NewsArticle;
import io.github.gabrielvavelar.NewsLetter.service.scraper.NewsFetcher;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class G1WebScraper implements NewsFetcher {

    @Override
    public List<NewsArticle> fetchLatestNews(int limit) {
        try {
            Document home = Jsoup.connect("https://g1.globo.com/")
                    .userAgent("Mozilla/5.0")
                    .get();

            List<String> articleUrls = home.select("a[href]")
                    .stream()
                    .map(a -> a.attr("abs:href"))
                    .filter(href -> href.endsWith(".ghtml"))
                    .distinct()
                    .limit(limit)
                    .toList();

            List<NewsArticle> articles = new ArrayList<>();

            for (String url : articleUrls) {
                try {
                    Document article = Jsoup.connect(url).get();
                    String title = article.select("h1").text();
                    String content = article.select("article").text();

                    articles.add(new NewsArticle(title, content, url));

                } catch (Exception e) {
                    log.warn("Failed to process article {}", url, e);
                }
            }

            return articles;

        } catch (Exception e) {
            throw new NewsScrapingException("Scraping error", e);
        }
    }
}
