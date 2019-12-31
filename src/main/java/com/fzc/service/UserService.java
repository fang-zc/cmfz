package com.fzc.service;

import com.fzc.entity.Article;
import com.fzc.entity.User;
import com.fzc.entity.UserCmfz;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserService
 * @Description
 * @Author
 * @Date 2019-12-20 14:44
 * @Version 1.0
 */
@Service
public interface UserService {

    //用户登录
    Map<String, Object> login(User user, HttpServletRequest request);

    //用户注册
    Map<String, Object> regist(User user);

    //修改用户性信息
    Map<String, Object> update(User user);

    //查询好友
    List<UserCmfz> showAllFriend(User user, HttpServletRequest request);

    //获取上师文章
    List<Article> showAllArticleByUp(User user);

    //展示所有用户信息
    Map<String, Object> showAllUser(Integer page, Integer rows);

    //修改用户状态
    void updateUser(User user);

    //展示所有用户
    List<User> showAll();

    List<Integer> showGirlCount();

    List<Integer> showBoyCount();
}
