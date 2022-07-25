package com.spring.news.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "news2") //db에 있는 테이블을 의미 // name은 테이블 이름 설정
@Data
public class News {

    @Id //primary키를 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mariadb에서 사용
    private Integer id;

    private String title;

    @Lob
    private String content;

}
