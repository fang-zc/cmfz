package com.fzc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ChapterCmfz
 * @Description
 * @Author
 * @Date 2019-12-26 17:01
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterCmfz {
    private String title;
    private String download_url;
    private String size;
    private String duration;
}
