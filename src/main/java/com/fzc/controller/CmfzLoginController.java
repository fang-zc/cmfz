package com.fzc.controller;

import com.fzc.entity.User;
import com.fzc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CmfzLoginController
 * @Description
 * @Author
 * @Date 2019-12-26 17:12
 * @Version 1.0
 */
@RestController
@RequestMapping("/account")
public class CmfzLoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Map<String, Object> login(String phone, String password, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = new User();
            user.setPassword(password);
            user.setC_phone(phone);
            map = userService.login(user, request);


            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "-200");
            map.put("errmsg", "密码错误");
            return map;
        }
    }

    @RequestMapping("/regist")
    public Map<String, Object> regist(String phone, String password) {
        Map<String, Object> map = new HashMap<>();

        try {
            User user = new User();
            user.setC_phone(phone);
            user.setPassword(password);
            map = userService.regist(user);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "-200");
            map.put("error_msg", "该手机号码已存在");
            return map;
        }
    }

    @RequestMapping("/modify")
    public Map<String, Object> modify(String uid, String gender) {
        User user = new User();
        user.setC_id(uid);
        user.setC_sex(gender);
        Map<String, Object> map = userService.update(user);
        return map;
    }
}
