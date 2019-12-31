package com.fzc.controller;

import com.fzc.entity.Album;
import com.fzc.entity.AlbumDetail;
import com.fzc.entity.Chapter;
import com.fzc.entity.ChapterCmfz;
import com.fzc.service.AlbumService;
import com.fzc.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DetailController
 * @Description
 * @Author
 * @Date 2019-12-26 16:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/wen")
    public Map<String, Object> wen(String id, String uid, HttpServletRequest request) throws UnknownHostException {
        Album album = new Album();
        album.setAlbum_id(id);
        AlbumDetail albumDetail = albumService.showAlbumById(album, request);
        Chapter chapter = new Chapter();
        chapter.setAlbum_id(id);
        List<ChapterCmfz> chapterCmfzs = chapterService.showChapterById(chapter, request);
        Map<String, Object> map = new HashMap<>();
        map.put("introduction", albumDetail);
        map.put("list", chapterCmfzs);
        return map;
    }
}
