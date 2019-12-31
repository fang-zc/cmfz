package com.fzc.controller;

import com.fzc.entity.User;
import com.fzc.entity.UserCmfz;
import com.fzc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName CmfzMemberController
 * @Description
 * @Author
 * @Date 2019-12-26 19:21
 * @Version 1.0
 */
@RestController
@RequestMapping("/member")
public class CmfzMemberController {
    @Autowired
    private UserService userService;

    @RequestMapping("/showMember")
    public List<UserCmfz> showMember(String uid, HttpServletRequest request) {
        User user = new User();
        user.setC_id(uid);
        List<UserCmfz> users = userService.showAllFriend(user, request);
        return users;
    }
}
