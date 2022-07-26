package com.spring.news.service;

import com.spring.news.entity.NewsC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.Date;

@EnableScheduling
@Configuration
public class Scheduler {

    @Autowired
    private NewsService newsService;

    @Scheduled(cron = "0 15 5 * * *")
//    @Scheduled(fixedDelay = 3600000)
    public void crawlingScheduler() throws IOException, InterruptedException {

        Date now = new Date();
        System.out.println("Scheduled start : " + now.toString());
        NewsCrawling newsCrawling = new NewsCrawling("test");
        for (NewsC newsC : newsCrawling.cronCrawling()) {
            newsService.crawlingWrite(new NewsC(newsC, TextRank.start(newsC.getContent())));
        }

        System.out.println("Scheduled end : " + now.toString());
    }
}
