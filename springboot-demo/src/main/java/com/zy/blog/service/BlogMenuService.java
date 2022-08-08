package com.zy.blog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.blog.model.BlogMenu;

/**
 * @Classname BlogMenuService
 * @Description TODO
 * @Date 2021/9/4 22:57
 * @Created by 飞燕
 */
public interface BlogMenuService{

    int addBlogMenu(BlogMenu blogMenu);


    int deleteById(Long id);

    int updateById(BlogMenu blogMenu);

    int update(BlogMenu blogMenu,Wrapper<BlogMenu> updateWrapper);

    BlogMenu selectById(Long id);

    IPage<BlogMenu> page(IPage page, Wrapper<BlogMenu> queryWrapper);
}
