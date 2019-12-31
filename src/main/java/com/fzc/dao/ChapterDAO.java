package com.fzc.dao;

import com.fzc.entity.Chapter;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ChapterDAO extends Mapper<Chapter> {
}
