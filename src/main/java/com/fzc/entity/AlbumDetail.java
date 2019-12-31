package com.fzc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName AlbumDetail
 * @Description
 * @Author
 * @Date 2019-12-26 16:31
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDetail {
    private String thumbnail;
    private String title;
    private Double score;
    private String author;
    private String broadcast;
    private String set_count;
    private String brief;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_date;
}
