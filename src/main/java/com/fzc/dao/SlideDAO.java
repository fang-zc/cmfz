package com.fzc.dao;

import com.fzc.entity.Slide;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface SlideDAO extends Mapper<Slide> {
}
