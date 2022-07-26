package com.spring.news.service;

import com.spring.news.entity.NewsC;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class NewsCrawling {

    private String url;

    public NewsCrawling(String url) {
        this.url = url;
    }

    public ArrayList<NewsC> crawlingNews() {

        ArrayList<NewsC> result = new ArrayList<>();

        Document document = null;

        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (document == null) {
            return result;
        }

        Elements elements = document.select("div.section_body");

        for (Element element : document.select("a")) {
            String tmp = element.attr("href");
            if (tmp.contains("https://n.news.naver.com/mnews/article")) {
                result.add(crawling(tmp));
            }
        }
        return result;
    }

    public NewsC crawling(String url) {

        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String title = document.select("div.media_end_head_title").text();
        String content = document.select("div.newsct_article").text();

        return new NewsC(url, title, content);
    }


    public ArrayList<NewsC> cronCrawling() {
        ArrayList<NewsC> list = new ArrayList<>();
        for (int i = 100; i < 108; i++) {
            url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=" + i;
            list.addAll(crawlingNews());
        }

        return list;
    }
}