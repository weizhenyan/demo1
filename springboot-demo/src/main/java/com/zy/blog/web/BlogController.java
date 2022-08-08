package com.zy.blog.web;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.blog.common.lang.Result;
import com.zy.blog.model.Blog;
import com.zy.blog.model.vo.BlogVo;
import com.zy.blog.service.BlogService;
import com.zy.blog.util.ShiroUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
/**
 * @Classname BlogController
 * @Description TODO
 * @Date 2021/9/4 23:08
 * @Created by 飞燕
 * @author 飞燕
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/index")
    public Object index() {
        Blog blog = blogService.getBlogById(1L);
        return Result.success(blog);
    }

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        //JSON.toJSONString(
        return Result.success(pageData);
    }


    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") String id) {
        System.out.println(id + "============================");
        Blog blog = blogService.getBlogById(Long.valueOf(id));
        Assert.notNull(blog, "博客已被删除");
        return Result.success(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
        Blog temp = null;
        if (blog.getId() != null) {
            temp = blog;
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getPofile().getId().longValue(), "没有权限编辑");
            BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
            blogService.updateBlog(temp);
        }
        System.out.println("=========================================================================");
        return Result.success(null);
    }

    @RequiresAuthentication
    @PostMapping("/blog/add")
    public Result add(@Validated @RequestBody Blog blog) {
        Blog temp = new Blog();
        temp.setUserId(ShiroUtil.getPofile().getId());
        temp.setCreated(new Date());
        temp.setStatus(0);
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.addBlog(temp);
        System.out.println("=========================================================================");
        return Result.success(null);
    }

    //    @AutoLog(value = "员工CURD-分页列表查询")
//    @ApiOperation(value="员工CURD-分页列表查询", notes="员工CURD-分页列表查询")
    @GetMapping(value = "/list")
    public Result queryPageList(
                                @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                HttpServletRequest req) {
        //QueryGenerator.initQueryWrapper是jeecg-boot封装的查询,默认支持所有复杂的检索查询
        QueryWrapper<BlogVo> queryWrapper = new QueryWrapper<>();
        //QueryGenerator.initQueryWrapper(employee, req.getParameterMap());
        System.out.println("================" + currentPage);
        Page<BlogVo> page = new Page<BlogVo>(currentPage, pageSize);
        //自定义getAll接口
        IPage<BlogVo> pageList = blogService.getAll(page, queryWrapper);
        return Result.success(pageList);
    }

    /**
     * 博客数据导出
     */
    @RequestMapping(value = "/blog/exportExcel", method = RequestMethod.POST)
    public void exportExcel(HttpServletResponse response, @PathVariable(name = "checkbox") String checkbox) {
        JSONArray ids = JSON.parseArray(checkbox);
        String fileName = "博客信息.xlsx";
        List<Blog> blogs = null;
        if (ids.size() <= 0) {
            //没有选择就直接导出
            blogs = blogService.getBlogs();
        } else {
//            List<Long> ids2 = ids.stream().map(s -> Long.parseLong(s.t)).collect(Collectors.toList());
            blogs = blogService.getBlogsByIds(ids.toJavaList(Long.class));
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("博客信息", "blog"),
                Blog.class, blogs);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
