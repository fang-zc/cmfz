package com.fzc.service;

import com.fzc.dao.AdminDAO;
import com.fzc.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @ClassName AdminServiceImpl
 * @Description
 * @Author
 * @Date 2019-12-17 15:37
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;

    @Override
    public void login(Admin admin, String code, HttpSession session) {
        System.out.println(code);
        if (session.getAttribute("code").equals(code)) {
            Admin admin1 = adminDAO.selectOne(admin);
            if (admin1 == null) throw new RuntimeException("用户账号或者密码错误");
            /*if(!admin.getPassword().equals(admin1.getPassword()))throw new RuntimeException("用户账号或者密码错误");*/
            /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();*/
            session.setAttribute("loginAdmin", admin1);
        } else {
            throw new RuntimeException("验证码错误");
        }
    }
}
