package com.fzc.dao;

import com.fzc.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserDAO extends Mapper<User> {
    Integer queryGirlPerviousFirst(); //前第一周女

    Integer queryGirlPerviousSecond(); //前第二周女

    Integer queryGirlPerviousThird(); //前第三周女

    Integer queryBoyPerviousFirst(); //前第一周男

    Integer queryBoyPerviousSecond(); //前第二周男

    Integer queryBoyPerviousThird(); //前第三周男
}
