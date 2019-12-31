package com.fzc.controller;

import com.fzc.entity.Admin;
import com.fzc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ClassName AdminController
 * @Description
 * @Author
 * @Date 2019-12-17 15:48
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(Admin admin, HttpSession session, String code) {
        try {
            System.out.println(admin);
            adminService.login(admin, code, session);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            String message = e.getMessage();
            return message;
        }
    }
}
/*
if(session.getAttribute("code").equals(code)){
        try {
        System.out.println(admin);
        adminService.login(admin);
        return "redirect:/index.jsp";
        } catch (Exception e) {
        e.printStackTrace();
        String message = e.getMessage();
        model.addAttribute("message",message);
        return "forward:/login/login.jsp";
        }
        }else{
        String message = "验证码错误";
        model.addAttribute("message",message);
        return "forward:/login/login.jsp";
        }*/
