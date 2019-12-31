package com.fzc.controller;

import com.fzc.entity.Article;
import com.fzc.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @ClassName ArticleController
 * @Description
 * @Author
 * @Date 2019-12-21 16:14
 * @Version 1.0
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/showAllArticle")
    public Map<String, Object> showAllArticle(Integer page, Integer rows) {
        Map<String, Object> map = articleService.showAllArticle(page, rows);
        return map;
    }

    @RequestMapping("/editArticle")
    public Map<String, Object> editArticle(String oper, Article article) {
        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            map = add(article);
        } else if (oper.equals("edit")) {
            map = edit(article);
        } else {
            map = delete(article);
        }
        return map;
    }

    public Map<String, Object> add(Article article) {
        Map<String, Object> map = new HashMap<>();
        try {
            String id = articleService.addArticle(article);
            map.put("status", true);
            map.put("message", id);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    public Map<String, Object> edit(Article article) {
        Map<String, Object> map = new HashMap<>();
        try {
            articleService.updateArticle(article);
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    public Map<String, Object> delete(Article article) {
        Map<String, Object> map = new HashMap<>();
        try {
            articleService.deleteArticle(article);
            map.put("status", true);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping("/addNews")
    public void String(String content, String ncontent) {
        System.out.println("content:" + content);
        System.out.println("ncontent:" + ncontent);
    }

    @RequestMapping("/upload")
    public Map<String, Object> upload(MultipartFile imgFile, HttpSession session, HttpServletRequest request) throws UnknownHostException {
        //上传图片
        String realPath = session.getServletContext().getRealPath("/article/img");
        String originalFilename = new Date().getTime() + imgFile.getOriginalFilename();
        try {
            imgFile.transferTo(new File(realPath + "/" + originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*{"error":0,"url":"\/ke4\/attached\/W020091124524510014093.jpg"}*/
        Map<String, Object> map = new HashMap<>();
        map.put("error", 0);
        String scheme = request.getScheme();//协议
        InetAddress localHost = InetAddress.getLocalHost();//获取ip
        String s = localHost.toString();
        String ip = s.split("/")[1];    //截取ip
        int serverPort = request.getServerPort();   //获取端口号 8585
        String contextPath = request.getContextPath();  //获取项目名cmfz
        String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/article/img/" + originalFilename;
        System.out.println(url);
        map.put("url", url);
        return map;
    }

    @RequestMapping("/getAll")
    public Map<String, Object> getAll(HttpSession session, HttpServletRequest request) throws UnknownHostException {
        Map<String, Object> map = new HashMap<>();
        String realPath = session.getServletContext().getRealPath("/article/img");
        File files = new File(realPath);
        String[] names = files.list();
        List<Map<String, Object>> list = new ArrayList<>();
        for (String name : names) {
            Map<String, Object> file = new HashMap<>();
            file.put("is_dir", false);
            file.put("has_file", false);
            File file1 = new File(realPath, name);  //获取该文件：即该图片
            file.put("filesize", file1.length());
            file.put("dir_path", "");
            file.put("is_photo", true);
            file.put("filetype", FilenameUtils.getExtension(name));
            file.put("filename", name);
            file.put("datetime", "2018-06-06 00:36:39");
            list.add(file);
        }
        map.put("moveup_dir_path", "");
        map.put("current_dir_path", "");
        String scheme = request.getScheme();
        InetAddress localHost = InetAddress.getLocalHost();
        String ip = localHost.toString().split("/")[1];
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/article/img/";
        map.put("current_url", url);
        map.put("total_count", names.length);
        map.put("file_list", list);
        return map;
    }
}
