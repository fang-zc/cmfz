package com.fzc.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.fzc.entity.User;
import com.fzc.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description
 * @Author
 * @Date 2019-12-20 14:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/showAllUser")
    public Map<String, Object> showAllUser(Integer page, Integer rows) {
        Map<String, Object> map = userService.showAllUser(page, rows);
        return map;
    }

    @RequestMapping("/editUser")
    public String userEdit(String oper, User user) {
        System.out.println(user);
        userService.updateUser(user);
        return "";
    }

    @RequestMapping("/userOut")
    public void userOut(HttpServletResponse response, HttpSession session) throws IOException {
        List<User> users = userService.showAll();
        //路径补全
        String realPath = session.getServletContext().getRealPath("/user/img");
        for (User user : users) {
            user.setC_photo(realPath + "//" + user.getC_photo());
            System.out.println("user头像路径:" + user.getC_photo());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("佛教弟子", "弟子名单"), User.class, users);
        //设置附件形式接收
        String encode = URLEncoder.encode("驰名法州.xls", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + encode);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/showCount")
    public Map<String, List<Integer>> showCount() {
        List<Integer> showGirlCount = userService.showGirlCount();
        List<Integer> showBoyCount = userService.showBoyCount();
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("girl", showGirlCount);
        map.put("boy", showBoyCount);
        /*JSONArray jsonArray = new JSONArray();
        jsonArray.add(map);
        String s = jsonArray.toString();*/
        /*GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-03eb8cfc24824db5a57134b8f2bd20c3");
        goEasy.publish("my_channel", map);*/
        /* System.out.println(s);*/
        return map;
    }
}
