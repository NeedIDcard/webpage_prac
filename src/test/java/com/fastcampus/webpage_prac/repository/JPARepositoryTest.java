package com.fastcampus.webpage_prac.repository;

import com.fastcampus.webpage_prac.config.JpaConfig;
import com.fastcampus.webpage_prac.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA connect test")
@Import(JpaConfig.class)
@DataJpaTest
class JPARepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JPARepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }


    @DisplayName("select test")
    @Test
    public void select_works_fine() throws Exception{
        //given

        //when
        List<Article> articles = articleRepository.findAll();
        //then
        assertThat(articles)
                .isNotNull()
                .hasSize(100);

    }

    @DisplayName("insert test")
    @Test
    public void insert_works_fine() throws Exception{
        //given
        long previosCount = articleRepository.count();
        Article article = Article.of("new article", "new content", "#spring");
        //when
        Article savedarticle = articleRepository.save(article);
        //then
        assertThat(articleRepository.count()).isEqualTo(previosCount + 1);

    }

    @DisplayName("update test")
    @Test
    public void update_works_fine() throws Exception{
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);
        //when
        Article savedarticle = articleRepository.saveAndFlush(article);
        //then
        assertThat(savedarticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);

    }

    @DisplayName("delete test")
    @Test
    public void delete_works_fine() throws Exception{
        //given

        Article article = articleRepository.findById(1L).orElseThrow();
        long previosCount = articleRepository.count();
        long previoscommentCount = articleCommentRepository.count();
        long deletedCommentsize = article.getArticleComments().size();
        //when
        articleRepository.delete(article);
        //then
        assertThat(articleRepository.count()).isEqualTo(previosCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previoscommentCount -deletedCommentsize);

    }

}