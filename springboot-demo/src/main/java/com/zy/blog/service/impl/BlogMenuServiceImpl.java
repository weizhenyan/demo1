package com.zy.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.blog.dao.BlogMenuMapper;
import com.zy.blog.model.BlogMenu;
import com.zy.blog.service.BlogMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname BlogMenuServiceImpl
 * @Description TODO
 * @Date 2021/9/4 22:57
 * @Created by 飞燕
 */
@Service
public class BlogMenuServiceImpl implements BlogMenuService {

    @Autowired
    private BlogMenuMapper blogMenuMapper;

    @Override
    public int addBlogMenu(BlogMenu blogMenu) {
        return blogMenuMapper.insert(blogMenu);
    }

    @Override
    public int deleteById(Long id) {
        return blogMenuMapper.deleteById(id);
    }

    @Override
    public int updateById(BlogMenu blogMenu) {
        return blogMenuMapper.updateById(blogMenu);
    }

    @Override
    public int update(BlogMenu blogMenu, Wrapper<BlogMenu> updateWrapper) {
        return blogMenuMapper.update(blogMenu,updateWrapper);
    }

    @Override
    public BlogMenu selectById(Long id) {
        return blogMenuMapper.selectById(id);
    }

    @Override
    public IPage<BlogMenu> page(IPage page, Wrapper<BlogMenu> queryWrapper) {
        return blogMenuMapper.selectMapsPage(page,queryWrapper);
    }
}
