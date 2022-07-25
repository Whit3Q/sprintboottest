package com.spring.news.service;

import com.spring.news.entity.News;
import com.spring.news.entity.NewsC;
import com.spring.news.repository.CrawlingRepository;
import com.spring.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CrawlingRepository crawlingRepository;

    //글 작성
    public void newsWrite(News news) {

        newsRepository.save(news);
    }

    // 게시글 리스트 처리
    public List<News> newsList() {

        return newsRepository.findAll();
    }

    // 요약 게시글 처리
    public List<NewsC> newsCList() {

        return crawlingRepository.findAll();
    }

    // 특정 게시글 불러오기
    public News newsView(Integer id) {

        Optional<News> tmp = newsRepository.findById(id);
        return tmp.orElse(null);

    }

    public void newsDelete(Integer id) {
        newsRepository.deleteById(id);
    }

    public ArrayList<NewsC> newsCrawling(String url) {
        NewsCrawling newsCrawling = new NewsCrawling(url);

        return newsCrawling.crawlingNews();
    }

    //글 작성
    public void crawlingWrite(NewsC newsC) {
        boolean check = crawlingRepository.existsByTitle(newsC.getTitle());
        if(!check) {
            crawlingRepository.save(newsC);
        }
    }
}
