package com.zy.blog.dao;

import com.zy.blog.model.TBlogMenu;

public interface TBlogMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TBlogMenu record);

    int insertSelective(TBlogMenu record);

    TBlogMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TBlogMenu record);

    int updateByPrimaryKey(TBlogMenu record);
}