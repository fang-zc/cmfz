package com.fzc.controller;

import com.fzc.entity.Slide;
import com.fzc.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName SlideController
 * @Description
 * @Author
 * @Date 2019-12-18 14:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/slide")
public class SlideController {
    @Autowired
    private SlideService slideService;

    @RequestMapping("/showAllSlide")
    public Map<String, Object> showAllSlide(Integer page, Integer rows) {
        Map<String, Object> map = slideService.showAllSlide(page, rows);
        return map;
    }

    @RequestMapping("/editSlide")
    public Map<String, Object> editSlide(String oper, Slide slide, HttpSession session) {
        String s = null;
        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            System.out.println("添加：" + slide);
            s = UUID.randomUUID().toString();
            slide.setSlide_id(s);
            slide.setSlide_create_date(new Date());
            String realPath = session.getServletContext().getRealPath("/image");
            slideService.addSlide(slide);
        } else if (oper.equals("del")) {
            slideService.deleteSlide(slide);
        } else {
            s = slide.getSlide_id();
            slide.setSlide_cover(null);
            slideService.updateSlide(slide);
        }
        map.put("message", s);
        return map;
    }

    @RequestMapping("/upload")
    public void upload(String slide_id, MultipartFile slide_cover, HttpSession session) throws IOException {
        System.out.println("id:" + slide_id);
        System.out.println("cover:" + slide_cover);
        //文件上传
        String realPath = session.getServletContext().getRealPath("/image");
        String originalFilename = slide_cover.getOriginalFilename();
        slide_cover.transferTo(new File(realPath + "/" + originalFilename));
        Slide slide = new Slide();
        slide.setSlide_id(slide_id);
        System.out.println("originalFilename:" + originalFilename);
        slide.setSlide_cover(originalFilename);
        System.out.println(slide);
        slideService.updateSlide(slide);
    }

}
