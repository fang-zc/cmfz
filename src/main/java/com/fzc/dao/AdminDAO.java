package com.fzc.dao;

import com.fzc.entity.Admin;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AdminDAO extends Mapper<Admin> {
}
