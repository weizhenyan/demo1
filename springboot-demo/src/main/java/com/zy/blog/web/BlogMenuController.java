package com.zy.blog.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.blog.common.lang.Result;
import com.zy.blog.model.BlogMenu;
import com.zy.blog.service.BlogMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname BlogMenuController
 * @Description TODO
 * @Date 2021/9/4 23:08
 * @Created by 飞燕
 * @author 飞燕
 */
@RestController
@RequestMapping("/blogMenu")
@Api(tags = "菜单项相关接口")
public class BlogMenuController {

    @Autowired
    private BlogMenuService blogMenuService;

    @RequiresAuthentication
    @PostMapping("/add")
    @ApiModelProperty(value = "添加菜单项")
    public Result addBlogMenu(@Validated @RequestBody BlogMenu blogMenu) {
        return Result.success(blogMenuService.addBlogMenu(blogMenu));
    }
    @GetMapping("/blogMenus")
    public Result getBlogMenus(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);
        IPage pageData = blogMenuService.page(page, new QueryWrapper<BlogMenu>().orderByDesc("created"));
        //JSON.toJSONString(
        return Result.success(pageData);
    }
}
