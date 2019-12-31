package com.fzc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UserCmfz {
    private String password;
    private String farmington;
    private String uid;
    private String nickname;
    private String gender;
    private String photo;
    private String localtion;
    private String province;
    private String city;
    private String description;
    private String phone;
}
