package com.fastcampus.webpage_prac.repository;

import com.fastcampus.webpage_prac.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}