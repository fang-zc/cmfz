package com.fzc.service;

import com.fzc.dao.AlbumDAO;
import com.fzc.dao.ChapterDAO;
import com.fzc.entity.Album;
import com.fzc.entity.Chapter;
import com.fzc.entity.ChapterCmfz;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @ClassName ChapterServiceImpl
 * @Description
 * @Author
 * @Date 2019-12-19 16:20
 * @Version 1.0
 */
@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDAO chapterDAO;
    @Autowired
    private AlbumDAO albumDAO;

    @Override
    public Map<String, Object> showAllChapter(Integer page, Integer rows, String album_id) {
        Map<String, Object> map = new HashMap<>();
        Chapter chapter = new Chapter();
        chapter.setAlbum_id(album_id);
        System.out.println("service:" + album_id);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Chapter> chapters = chapterDAO.selectByRowBounds(chapter, rowBounds);
        if (chapters.size() == 0 && page > 1) {
            page--;
            rowBounds = new RowBounds((page - 1) * rows, rows);
            chapters = chapterDAO.selectByRowBounds(chapter, rowBounds);
        }
        int i = chapterDAO.selectCount(chapter);
        Integer total = i % rows == 0 ? i / rows : i / rows + 1;
        map.put("rows", chapters);
        map.put("page", page);
        map.put("total", total);
        map.put("records", i);
        return map;
    }

    @Override
    public String addChapter(Chapter chapter) {
        String id = UUID.randomUUID().toString();
        //添加id   添加时间
        chapter.setChapter_id(id);
        chapter.setChapter_create_date(new Date());
        //计算数量
        Chapter chapter1 = new Chapter();
        chapter1.setAlbum_id(chapter.getAlbum_id());
        System.out.println("chapter1:" + chapter1);
        int i1 = chapterDAO.selectCount(chapter1) + 1;
        /*Example example = new Example(Chapter.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("album_id",chapter.getAlbum_id());*/


        /*int i1 = chapterDAO.selectCountByExample(example);*/

        /*List<Chapter> chapters = chapterDAO.selectByExample(chapter1);
        int count =chapters.size();*/

        /*chapter.setAlbum_id(chapter.getAlbum_id());*/
        Album album = new Album();
        album.setAlbum_id(chapter.getAlbum_id());
        album.setAlbum_count(i1 + "");
        System.out.println("当前专辑总数:" + i1);
        System.out.println("album:" + album);
        albumDAO.updateByPrimaryKeySelective(album);

        int i = chapterDAO.insertSelective(chapter);
        if (i == 0) {
            throw new RuntimeException("添加失败");
        }
        return id;
    }

    @Override
    public void updateChapter(Chapter chapter) {
        int i = chapterDAO.updateByPrimaryKeySelective(chapter);
        if (i == 0) {
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public List<ChapterCmfz> showChapterById(Chapter chapter, HttpServletRequest request) {
        List<Chapter> chapters = chapterDAO.select(chapter);
        ArrayList<ChapterCmfz> chapterCmfzs = new ArrayList<>();
        for (Chapter chapter1 : chapters) {
            ChapterCmfz chapterCmfz = new ChapterCmfz();
            //获取图片全路径
            String scheme = request.getScheme();//协议
            InetAddress localHost = null;//获取ip
            try {
                localHost = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            String s = localHost.toString();
            String ip = s.split("/")[1];    //截取ip
            int serverPort = request.getServerPort();   //获取端口号 8585
            String contextPath = request.getContextPath();  //获取项目名cmfz
            String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/article/img/" + chapter1.getChapter_cover();
            chapterCmfz.setDownload_url(url);
            chapterCmfz.setDuration(chapter1.getChapter_duration());
            chapterCmfz.setSize(chapter1.getChapter_size());
            chapterCmfz.setTitle(chapter1.getChapter_title());
            chapterCmfzs.add(chapterCmfz);
        }
        return chapterCmfzs;
    }
}
