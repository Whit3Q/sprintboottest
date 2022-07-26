package com.spring.news.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "news")
@Data
public class NewsC {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    private String title;

    private String content;

    private String contentsummery;


    public NewsC(String url, String title, String content) {
        this.url = url;
        this.title = title;
        this.content = content;
    }

    public NewsC(NewsC newsC, String contentsummery) {
        this.url = newsC.url;
        this.title = newsC.title;
        this.content = newsC.content;
        this.contentsummery = contentsummery;
    }
}
