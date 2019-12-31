package com.fzc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName AlbumCmfz
 * @Description
 * @Author
 * @Date 2019-12-26 16:11
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumCmfz {
    private String thumbnail;
    private String title;    //标题
    private String author;        //描述
    private String type;    //类型（0：闻，1：思）
    private String set_count;//集数（只有闻的数据才有）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_date;    //创建时间
}
