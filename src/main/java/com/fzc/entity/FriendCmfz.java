package com.fzc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName FriendCmfz
 * @Description
 * @Author
 * @Date 2019-12-26 19:44
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "friend")
public class FriendCmfz {
    @Id
    private String fid;
    private String c_id;
    private String my_id;
}
