package com.fzc.dao;

import com.fzc.entity.Article;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName ArticleDAO
 * @Description
 * @Author
 * @Date 2019-12-21 15:56
 * @Version 1.0
 */
@Repository
public interface ArticleDAO extends Mapper<Article> {
}
