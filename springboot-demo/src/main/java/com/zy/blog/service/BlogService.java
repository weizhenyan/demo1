package com.zy.blog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.blog.model.Blog;
import com.zy.blog.model.vo.BlogVo;

import java.util.List;

public interface BlogService {
    Blog getBlogById(Long id);
    int deleteById(Long id);

    int addBlog(Blog record);

    List<Blog> getBlogs();

    int updateBlog(Blog record);

    IPage<Blog> page(IPage page, Wrapper<Blog> queryWrapper);
    IPage<Blog> page2(IPage page,int state);
    List<Blog> getBlogsByIds(List<Long> idList);

    public IPage<BlogVo> getAll(Page<BlogVo> page, QueryWrapper<BlogVo> queryWrapper);
}
