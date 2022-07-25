package com.spring.news.repository;


import com.spring.news.entity.NewsC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlingRepository extends JpaRepository<NewsC, Integer> {

    boolean existsByTitle(String title);
}
