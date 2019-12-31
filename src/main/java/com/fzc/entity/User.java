package com.fzc.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName User
 * @Description
 * @Author
 * @Date 2019-12-20 14:38
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "c_user")
public class User {
    @Id
    @ExcelIgnore
    private String c_id;
    @Excel(name = "用户名")
    private String username;
    private String password;
    private String c_salt;
    @Excel(name = "性别")
    private String c_sex;
    @Excel(name = "法号")
    private String c_dharma;
    @Excel(name = "省份")
    private String c_province;
    @Excel(name = "城市")
    private String c_city;
    @Excel(name = "签名")
    private String c_sign;
    @Excel(name = "头像", type = 2, width = 40, height = 40)
    private String c_photo;
    @Excel(name = "状态")
    private String c_status;
    @Excel(name = "联系方式")
    private String c_phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "注册时间", format = "yyyy年MM月dd日")
    private Date c_create_date;
    @Excel(name = "上师")
    private String u_name;      //上师名
    @Excel(name = "上师id")
    private String u_id;      //上师名
}
