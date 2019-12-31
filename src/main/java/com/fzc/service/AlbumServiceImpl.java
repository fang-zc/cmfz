package com.fzc.service;

import com.fzc.dao.AlbumDAO;
import com.fzc.entity.Album;
import com.fzc.entity.AlbumCmfz;
import com.fzc.entity.AlbumDetail;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @ClassName AlbumServiceImpl
 * @Description
 * @Author
 * @Date 2019-12-19 15:47
 * @Version 1.0
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDAO albumDAO;

    @Override
    public Map<String, Object> showAllAlbum(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Album album = new Album();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Album> albums = albumDAO.selectByRowBounds(album, rowBounds);
        if (albums.size() == 0 && page > 1) {
            page--;
            rowBounds = new RowBounds((page - 1) * rows, rows);
            albums = albumDAO.selectByRowBounds(album, rowBounds);
        }
        int i = albumDAO.selectCount(album);
        map.put("rows", albums);
        map.put("page", page);
        map.put("total", i % rows == 0 ? i / rows : i / rows + 1);
        map.put("records", albumDAO.selectCount(album));
        return map;
    }

    @Override
    public String addAlbum(Album album) {
        String id = UUID.randomUUID().toString();
        album.setAlbum_id(id);
        album.setAlbum_create_date(new Date());
        album.setAlbum_count("0");
        int i = albumDAO.insertSelective(album);

        if (i == 0) {
            throw new RuntimeException("添加失败");
        }
        return id;
    }

    @Override
    public void updateAlbum(Album album) {
        int i = albumDAO.updateByPrimaryKeySelective(album);
        if (i == 0) {
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public List<AlbumCmfz> showAllAlbumCmfz(HttpServletRequest request) {
        Album album = new Album();
        List<Album> albums = albumDAO.select(album);
        List<AlbumCmfz> cmfzArrayLsit = new ArrayList<>();
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
        for (Album album1 : albums) {
            AlbumCmfz albumCmfz = new AlbumCmfz();
            albumCmfz.setAuthor(album1.getAlbum_author());
            albumCmfz.setCreate_date(album1.getAlbum_create_date());
            albumCmfz.setSet_count(album1.getAlbum_count());
            String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/article/img/" + album1.getAlbum_cover();
            albumCmfz.setThumbnail(url);
            albumCmfz.setTitle(album1.getAlbum_title());
            albumCmfz.setType("1");
            cmfzArrayLsit.add(albumCmfz);
        }
        return cmfzArrayLsit;
    }

    @Override
    public AlbumDetail showAlbumById(Album album, HttpServletRequest request) throws UnknownHostException {
        Album album1 = albumDAO.selectByPrimaryKey(album);
        AlbumDetail albumDetail = new AlbumDetail();
        albumDetail.setAuthor(album1.getAlbum_author());
        albumDetail.setBrief(album1.getAlbum_intro());
        albumDetail.setBroadcast(album1.getAlbum_beam());
        albumDetail.setCreate_date(album1.getAlbum_create_date());
        albumDetail.setScore(album1.getAlbum_score());
        albumDetail.setSet_count(album1.getAlbum_count());
        String url = null;
        //获取图片全路径
        String scheme = request.getScheme();//协议
        InetAddress localHost = InetAddress.getLocalHost();//获取ip
        String s = localHost.toString();
        String ip = s.split("/")[1];    //截取ip
        int serverPort = request.getServerPort();   //获取端口号 8585
        String contextPath = request.getContextPath();  //获取项目名cmfz
        url = scheme + "://" + ip + ":" + serverPort + contextPath + "/article/img/" + album1.getAlbum_cover();
        albumDetail.setThumbnail(url);
        albumDetail.setTitle(album1.getAlbum_title());
        return albumDetail;
    }
}
