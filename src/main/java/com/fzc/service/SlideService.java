package com.fzc.service;

import com.fzc.entity.Slide;
import com.fzc.entity.SlideCmfz;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface SlideService {
    //展示说有轮播图
    Map<String, Object> showAllSlide(Integer page, Integer rows);

    //轮播图总数
    /*Integer showTotalSlide();
    //轮播图总页数
    Integer showTotalPage(Integer rows);*/
    //编辑轮播图信息
    //添加轮播图
    void addSlide(Slide slide);

    //修改轮播图
    void updateSlide(Slide slide);

    //删除轮播图
    void deleteSlide(Slide slide);

    //展示所有符合条件轮播图
    List<SlideCmfz> showAllSlideCmfz(HttpServletRequest request);
}
