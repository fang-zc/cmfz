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
 * @ClassName Slide
 * @Description
 * @Author
 * @Date 2019-12-18 13:59
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "slide")
public class Slide {
    @Id
    private String slide_id;
    private String slide_name;
    private String slide_cover;
    private String slide_description;
    private String slide_status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /*@JSONField(format = "yyyy-MM-dd HH:mm:ss")*/
    private Date slide_create_date;
}
