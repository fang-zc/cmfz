package com.fzc.service;

import com.fzc.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    //展示所有文章
    Map<String, Object> showAllArticle(Integer page, Integer rows);

    //增
    String addArticle(Article article);

    //删
    void deleteArticle(Article article);

    //改
    void updateArticle(Article article);

    List<Article> showAll();
}
