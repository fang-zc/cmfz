package com.fzc.service;

import com.fzc.entity.Chapter;
import com.fzc.entity.ChapterCmfz;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ChapterService {
    Map<String, Object> showAllChapter(Integer page, Integer rows, String album_id);

    //添加专辑
    String addChapter(Chapter chapter);

    //修改专辑
    void updateChapter(Chapter chapter);

    //根据id查询出符合条件章节
    List<ChapterCmfz> showChapterById(Chapter chapter, HttpServletRequest request);
}
