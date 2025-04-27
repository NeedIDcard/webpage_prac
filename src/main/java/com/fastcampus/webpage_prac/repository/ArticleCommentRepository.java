package com.fastcampus.webpage_prac.repository;

import com.fastcampus.webpage_prac.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}