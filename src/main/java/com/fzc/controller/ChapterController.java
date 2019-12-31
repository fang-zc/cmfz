package com.fzc.controller;

import com.fzc.entity.Chapter;
import com.fzc.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ChapterController
 * @Description
 * @Author
 * @Date 2019-12-19 16:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/showAllChapter")
    public Map<String, Object> showAllChapter(Integer page, Integer rows, String album_id) {
        Map<String, Object> map = chapterService.showAllChapter(page, rows, album_id);
        return map;
    }

    @RequestMapping("/editChapter")
    public Map<String, Object> editChapter(String oper, Chapter chapter, String album_id) {
        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            System.out.println("父类id" + album_id);
            chapter.setAlbum_id(album_id);
            map = add(chapter);
        } else if (oper.equals("edit")) {
            map = update(chapter);
        }
        return map;
    }

    public Map<String, Object> add(Chapter chapter) {
        Map<String, Object> map = new HashMap<>();
        try {
            String id = chapterService.addChapter(chapter);
            map.put("message", id);
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", e.getMessage());
            map.put("status", false);
        }
        return map;
    }

    public Map<String, Object> update(Chapter chapter) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (chapter.getChapter_cover().equals("")) {
                chapter.setChapter_cover(null);
            }
            chapterService.updateChapter(chapter);
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", e.getMessage());
            map.put("status", false);
        }
        return map;
    }

    @RequestMapping("/upload")
    public void upload(String chapter_id, MultipartFile chapter_cover, HttpSession session) {
        /*获取时长*/
        Encoder encoder = new Encoder();
        long ls = 0;
        MultimediaInfo m;

        System.out.println("album_table:" + chapter_id);
        System.out.println("album_cover:" + chapter_cover);
        //获取绝对路径
        String realPath = session.getServletContext().getRealPath("/album/music");
        //获取文件名
        String originalFilename = chapter_cover.getOriginalFilename();
        String music = originalFilename + new Date().getTime();
        Chapter chapter = new Chapter();
        try {
            //上传文件
            File file = new File(realPath + "/" + music);
            File chapter_cover1 = convert(chapter_cover);
            m = encoder.getInfo(chapter_cover1);
            System.out.println("m:" + m);
            ls = m.getDuration() / 1000;    //得到一个long类型的时长
            chapter_cover.transferTo(new File(realPath + "/" + music));

            System.out.println("时长ls：" + ls);

            //获取文件大小
            //file.length可以获取文件字节大小，保留两位小数
            double size = file.length() / 1024.0 / 1024.0;
            size = (double) Math.round(size * 100) / 100;
            System.out.println("文件大小" + size + "MB");
            //更新数据
            chapter.setChapter_id(chapter_id);
            chapter.setChapter_cover(music);
            chapter.setChapter_size("" + size + "MB");//大小
            chapter.setChapter_duration(String.valueOf(ls / 60) + ":" + String.valueOf(ls % 60)); //转换为分钟：秒
            System.out.println("准备更新chapter：" + chapter);
            chapterService.updateChapter(chapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
