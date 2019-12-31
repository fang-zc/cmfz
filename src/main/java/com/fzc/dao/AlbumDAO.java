package com.fzc.dao;

import com.fzc.entity.Album;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AlbumDAO extends Mapper<Album> {
}
