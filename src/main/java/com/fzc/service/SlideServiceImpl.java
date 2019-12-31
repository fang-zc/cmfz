package com.fzc.service;

import com.fzc.dao.SlideDAO;
import com.fzc.entity.Slide;
import com.fzc.entity.SlideCmfz;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SlideServiceImpl
 * @Description
 * @Author
 * @Date 2019-12-18 14:47
 * @Version 1.0
 */
@Service
public class SlideServiceImpl implements SlideService {
    @Autowired
    private SlideDAO slideDAO;

    @Override
    public Map<String, Object> showAllSlide(Integer page, Integer rows) {
        Slide slide = new Slide();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Slide> slides = slideDAO.selectByRowBounds(slide, rowBounds);
        if (slides.size() == 0 && page > 1) {
            page--;
            rowBounds = new RowBounds((page - 1) * rows, rows);
            slides = slideDAO.selectByRowBounds(slide, rowBounds);
        }
        int i = slideDAO.selectCount(slide);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", slides);
        map.put("page", page);
        map.put("total", i % rows == 0 ? i / rows : i / rows + 1);
        map.put("records", i);

        return map;
    }

    /*@Override
    public Integer showTotalSlide() {
        List<Slide> slides = slideDAO.selectAll();

        return slides.size();
    }

    @Override
    public Integer showTotalPage(Integer rows) {
        List<Slide> slides = slideDAO.selectAll();
        Integer size = slides.size();
        Integer totalPage=size%rows==0?size/rows:size/rows+1;
        return totalPage;
    }*/

    @Override
    public void addSlide(Slide slide) {
        slideDAO.insertSelective(slide);
    }

    @Override
    public void updateSlide(Slide slide) {
        slideDAO.updateByPrimaryKeySelective(slide);
    }

    @Override
    public void deleteSlide(Slide slide) {
        slideDAO.delete(slide);
    }

    @Override
    public List<SlideCmfz> showAllSlideCmfz(HttpServletRequest request) {
        Slide slide = new Slide();
        slide.setSlide_status("1");
        List<Slide> slides = slideDAO.select(slide);
        List<SlideCmfz> slideCmfzs = new ArrayList<SlideCmfz>();
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

        for (Slide slide1 : slides) {
            String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/article/img/" + slide1.getSlide_cover();
            SlideCmfz slideCmfz = new SlideCmfz(slide1.getSlide_id(), slide1.getSlide_description(), url);
            slideCmfzs.add(slideCmfz);
        }
        return slideCmfzs;
    }
}
