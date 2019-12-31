package com.fzc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName Article
 * @Description
 * @Author
 * @Date 2019-12-21 15:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "article")
public class Article {
    @Id
    private String article_id;
    private String article_title;
    private String article_content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date article_create_date;
    private String article_author;
    private String u_id;
}
