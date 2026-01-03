package io.github.gabrielvavelar.Noticia.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsScheduler {
    private final NewsService newsService;

    @Scheduled(cron = "0 0 7 * * *")
    public void runDailyNews() {
        newsService.fetchNews();
    }
}
