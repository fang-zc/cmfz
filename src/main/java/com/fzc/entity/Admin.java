package com.fzc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName Admin
 * @Description
 * @Author
 * @Date 2019-12-17 14:57
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin")
public class Admin implements Serializable {
    @Id
    private String id;
    private String username;
    private String password;
    private String nickname;
}
