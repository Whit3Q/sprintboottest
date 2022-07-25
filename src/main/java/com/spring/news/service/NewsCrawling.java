package com.spring.news.service;

import com.spring.news.entity.NewsC;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;



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

        if(document == null) {
            return result;
        }

        Elements elements = document.select("div.section_body");

        for (Element element : document.select("a")) {
            String tmp = element.attr("href");
            if(tmp.contains("https://n.news.naver.com/mnews/article")) {
                result.add(crawling( tmp));
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

        return new NewsC( url, title,content);
    }

}