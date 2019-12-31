package com.fzc.controller;

import com.fzc.entity.Album;
import com.fzc.service.AlbumService;
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

/**
 * @ClassName AlbumController
 * @Description
 * @Author
 * @Date 2019-12-19 15:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/showAllAlbum")
    public Map<String, Object> showAllAlbum(Integer page, Integer rows) {
        Map<String, Object> map = albumService.showAllAlbum(page, rows);
        return map;
    }

    @RequestMapping("/editAlbum")
    public Map<String, Object> editAlbum(String oper, Album album) {
        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            map = add(album);
        } else if (oper.equals("edit")) {
            map = update(album);
        }
        return map;
    }

    public Map<String, Object> add(Album album) {
        Map<String, Object> map = new HashMap<>();
        try {
            String id = albumService.addAlbum(album);
            map.put("message", id);
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", e.getMessage());
            map.put("status", false);
        }
        return map;
    }

    public Map<String, Object> update(Album album) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (album.getAlbum_cover().equals("")) {
                album.setAlbum_cover(null);
            }
            albumService.updateAlbum(album);
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", e.getMessage());
            map.put("status", false);
        }
        return map;
    }

    @RequestMapping("/upload")
    public void upload(String album_id, MultipartFile album_cover, HttpSession session) {
        System.out.println("album_table:" + album_id);
        System.out.println("album_cover:" + album_cover);
        String realPath = session.getServletContext().getRealPath("/album/img");
        String originalFilename = album_cover.getOriginalFilename();
        String image = originalFilename + new Date().getTime();
        Album album = new Album();
        try {
            album_cover.transferTo(new File(realPath + "/" + image));
            album.setAlbum_id(album_id);
            album.setAlbum_cover(image);
            albumService.updateAlbum(album);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
