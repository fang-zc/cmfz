package com.fzc.controller;

import com.fzc.entity.AlbumCmfz;
import com.fzc.entity.Article;
import com.fzc.entity.SlideCmfz;
import com.fzc.entity.User;
import com.fzc.service.AlbumService;
import com.fzc.service.ArticleService;
import com.fzc.service.SlideService;
import com.fzc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CmfzController
 * @Description
 * @Author
 * @Date 2019-12-26 15:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/cmfz")
public class CmfzController {
    @Autowired
    private SlideService slideService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/first_page")
    public Map<String, Object> first_page(String uid, String type, String sub_type, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = new User();
        user.setC_id(uid);
        if (uid == null || type == null) {
            map.put("error", "参数不能为空");
        } else {
            if (type.equals("all")) {
                List<SlideCmfz> slideCmfzs = slideService.showAllSlideCmfz(request);
                map.put("header", slideCmfzs);
            }
            if (type.equals("wen")) {
                List<AlbumCmfz> albumCmfzs = albumService.showAllAlbumCmfz(request);
                map.put("album", albumCmfzs);
            }
            if (type.equals("si")) {
                if (sub_type == null) {
                    map.put("error", "sub_type不能为空");
                } else {
                    if (sub_type.equals("ssyj")) {
                        List<Article> articles = userService.showAllArticleByUp(user);
                        map.put("artical", articles);
                    }
                    if (sub_type.equals("xmfy")) {
                        List<Article> articles = articleService.showAll();
                        map.put("artical", articles);
                    }
                }

            }
        }
        return map;
    }
}
