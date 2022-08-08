package com.zy.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.blog.dao.BlogMapper;
import com.zy.blog.model.Blog;
import com.zy.blog.model.vo.BlogVo;
import com.zy.blog.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;
    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.selectById(id);
    }

    @Override
    public int deleteById(Long id) {
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int addBlog(Blog record) {
        return blogMapper.insert(record);
    }

    @Override
    public List<Blog> getBlogs() {
        return blogMapper.selectAll();
    }

    @Override
    public int updateBlog(Blog record) {
        return blogMapper.updateByPrimaryKey(record);
    }

    @Override
    public IPage<Blog> page(IPage page, Wrapper<Blog> queryWrapper) {
        return blogMapper.selectMapsPage(page,queryWrapper);
    }

    @Override
    public IPage<Blog> page2(IPage page, int state) {
        return blogMapper.selectPage(page,state);
    }

    @Override
    public IPage<BlogVo> getAll(Page<BlogVo> page, QueryWrapper<BlogVo> queryWrapper) {
        return blogMapper.findByPage(page,queryWrapper);
    }

    @Override
    public List<Blog> getBlogsByIds(List<Long> idList) {
        return blogMapper.findByIds(idList);
    }
}
