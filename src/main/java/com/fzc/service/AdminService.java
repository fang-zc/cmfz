package com.fzc.service;

import com.fzc.entity.Admin;

import javax.servlet.http.HttpSession;

public interface AdminService {
    //管理员登录
    void login(Admin admin, String code, HttpSession session);
}
