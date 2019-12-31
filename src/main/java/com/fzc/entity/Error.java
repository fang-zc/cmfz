package com.fzc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Error
 * @Description
 * @Author
 * @Date 2019-12-26 17:38
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private String error;
    private String error_msg;
    private String errmsg;
}
