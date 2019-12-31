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
 * @ClassName Chapter
 * @Description
 * @Author
 * @Date 2019-12-19 15:17
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chapter")
public class Chapter {
    @Id
    private String chapter_id;
    private String chapter_title;
    private String chapter_size;
    private String chapter_duration;
    private String chapter_cover;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date chapter_create_date;
    private String album_id;
}
