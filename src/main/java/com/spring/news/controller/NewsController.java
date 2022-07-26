package com.spring.news.controller;

import com.spring.news.entity.News;
import com.spring.news.entity.NewsC;
import com.spring.news.service.NewsService;
import com.spring.news.service.TextRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;


@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/")
    public String mainPage() {
        return "newsMain";
    }

    @GetMapping("/news/write")
    public String newsWriteFrom() {

        return "newsWrite";
    }

    @PostMapping("/news/write")
    public String newsWrite(News news) {

        newsService.newsWrite(news);

        return "newsMain";
    }

    @GetMapping("/news/list")
    public String newsList(Model model) {

        model.addAttribute("list", newsService.newsList());

        return "newsList";
    }

    @GetMapping("/newsC/list")
    public String newsCList(Model model) {

        model.addAttribute("list", newsService.newsCList());

        return "crawlingList";
    }

    @GetMapping("/news/view")
    public String newsView(Model model, Integer id) {

        model.addAttribute("news", newsService.newsView(id));

        return "newsView";
    }


    @PostMapping("/news/delete")
    public String newsDelete(Integer id) {

        newsService.newsDelete(id);

        return "redirect:/news/list";
    }

    @GetMapping("/news/modify/{id}")
    public String newsModify(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("news", newsService.newsView(id));

        return "newsModify";
    }

    @PostMapping("/news/update/{id}")
    public String newsUpdate(@PathVariable("id") Integer id, News news) {

        News tmp = newsService.newsView(id);
        tmp.setTitle(news.getTitle());
        tmp.setContent(news.getContent());

        newsService.newsWrite(tmp);

        return "redirect:/news/list";
    }

    @GetMapping("/news/crawlingMain")
    public String newsCrawlingMain() {

        return "crawling";
    }

    @GetMapping("/news/crawling")
    public String newsCrawling(Model model, String url) throws IOException, InterruptedException {

        ArrayList<NewsC> tmp = newsService.newsCrawling(url);
        for (NewsC newsC : tmp) {
            newsService.crawlingWrite(new NewsC(newsC, TextRank.start(newsC.getContent())));
        }

        return "redirect:/newsC/list";
    }

}
