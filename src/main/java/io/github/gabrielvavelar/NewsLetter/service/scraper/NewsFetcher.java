package io.github.gabrielvavelar.NewsLetter.service.scraper;

import io.github.gabrielvavelar.NewsLetter.model.NewsArticle;

import java.util.List;

public interface NewsFetcher {
    List<NewsArticle> fetchLatestNews(int limit);
}
