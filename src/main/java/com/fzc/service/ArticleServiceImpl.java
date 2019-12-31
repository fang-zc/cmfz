package com.fzc.service;

import com.fzc.dao.ArticleDAO;
import com.fzc.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName ArticleServiceImpl
 * @Description
 * @Author
 * @Date 2019-12-21 16:01
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Map<String, Object> showAllArticle(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Article article = new Article();
        //一共多少条数据
        int records = articleDAO.selectCount(article);
        //文章全部数据对象
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Article> articles = articleDAO.selectByRowBounds(article, rowBounds);
        if (articles.size() == 0 && page > 1) {
            page--;
            rowBounds = new RowBounds((page - 1) * rows, rows);
            articles = articleDAO.selectByRowBounds(article, rowBounds);
        }
        //总页数
        int total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("rows", articles);
        map.put("page", page);
        map.put("records", records);
        map.put("total", total);
        return map;
    }

    @Override
    public String addArticle(Article article) {
        String id = UUID.randomUUID().toString();
        article.setArticle_id(id);
        article.setArticle_create_date(new Date());
        int i = articleDAO.insertSelective(article);
        if (i == 0) {
            throw new RuntimeException("文章未添加成功");
        }
        return id;
    }

    @Override
    public void deleteArticle(Article article) {
        int i = articleDAO.delete(article);
        if (i == 0) {
            throw new RuntimeException("文章未删除成功");
        }
    }

    @Override
    public void updateArticle(Article article) {
        int i = articleDAO.updateByPrimaryKeySelective(article);
        if (i == 0) {
            throw new RuntimeException("文章未更新成功");
        }
    }

    @Override
    public List<Article> showAll() {
        Article article = new Article();
        List<Article> articles = articleDAO.select(article);
        return articles;
    }

}
