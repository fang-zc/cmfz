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
 * @ClassName Album
 * @Description
 * @Author
 * @Date 2019-12-19 14:43
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
public class Album {
    @Id
    private String album_id;
    private String album_title;
    private String album_intro;
    private String album_author;
    private String album_beam;
    private String album_cover;
    private String album_count;
    private Double album_score;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date album_create_date;
}
