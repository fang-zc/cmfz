package com.fzc;

import com.fzc.dao.AdminDAO;
import com.fzc.dao.ChapterDAO;
import com.fzc.entity.Admin;
import com.fzc.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmfzApplicationTests {
    @Autowired
    private AdminDAO adminDAO;
    @Autowired
    private ChapterDAO chapterDAO;

    @Test
    public void test() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        Admin selectOne = adminDAO.selectOne(admin);
        System.out.println(selectOne);
    }

    @Test
    public void test1() {
        Admin admin = new Admin();
        admin.setId("2");
        admin.setPassword("123");
        admin.setNickname("小黑");
        admin.setUsername("xiaohai");
        adminDAO.insert(admin);

    }

    @Test
    public void test2() {
        Integer page = 1;
        Integer rows = 20;
        Chapter chapter = new Chapter();
        chapter.setAlbum_id("1");
        List<Chapter> select = chapterDAO.select(chapter);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Chapter> chapters = chapterDAO.selectByRowBounds(chapter, rowBounds);
        for (Chapter chapter1 : chapters) {
            System.out.println(chapter1);
        }

    }
}
