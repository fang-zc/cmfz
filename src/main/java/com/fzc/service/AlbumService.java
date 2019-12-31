package com.fzc.service;

import com.fzc.entity.Album;
import com.fzc.entity.AlbumCmfz;
import com.fzc.entity.AlbumDetail;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AlbumService
 * @Description
 * @Author
 * @Date 2019-12-19 15:46
 * @Version 1.0
 */
public interface AlbumService {
    //展示所有专辑
    Map<String, Object> showAllAlbum(Integer page, Integer rows);

    //添加专辑
    String addAlbum(Album album);

    //修改专辑
    void updateAlbum(Album album);

    //展示所有专辑
    List<AlbumCmfz> showAllAlbumCmfz(HttpServletRequest request);

    //根据id获取专辑
    AlbumDetail showAlbumById(Album album, HttpServletRequest request) throws UnknownHostException;
}
