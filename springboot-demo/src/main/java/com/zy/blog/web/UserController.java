package com.zy.blog.web;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.zy.blog.common.lang.Result;
import com.zy.blog.constant.StringConstant;
import com.zy.blog.dto.user.QueryUserParamDto;
import com.zy.blog.model.User;
import com.zy.blog.model.domain.SexEnum;
import com.zy.blog.model.domain.UserType;
import com.zy.blog.service.UserService;
import com.zy.blog.util.Md5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
/**
 * @Classname UserController
 * @Description TODO
 * @Date 2021/9/4 23:08
 * @Created by 飞燕
 * @author 飞燕
 */
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户信息接口")
@ApiSort(101)
public class UserController {

    @Autowired
    UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    @ApiOperation(value = "新增/修改", httpMethod = "GET")
    @ApiOperationSupport(order = 1)
    public Object index() {
        User user = userService.getUserById(1L);
        return Result.success(user);
    }

//    @RequiresRoles({"admin"})
    @RequiresAuthentication
    @GetMapping("/getUserByParam")
    public Result getUsersByParam(
            QueryUserParamDto queryUserParamDto) {
        Long currentPage=queryUserParamDto.getCurrentPage();
        Long pageSize=queryUserParamDto.getPageSize();
        Long pageNum=(currentPage-1)*pageSize;
        queryUserParamDto.setCurrentPage(pageNum);
        return Result.success(userService.getUserByParam(queryUserParamDto));
    }
//    @RequiresRoles({"admin"})
    @RequiresAuthentication
    @GetMapping(value = "/getUserList")
    public Result queryPageList(
                                @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> page = new Page<>(currentPage,pageSize);
        IPage<User> userIPage = userService.getAll(page, queryWrapper);
        return Result.success(userIPage);
    }
    @PostMapping("/register")
    public Result register(@Validated @RequestBody User user) {
        User oneUser = userService.getUserByName(user.getUsername());
        if(oneUser!=null) {
            return Result.fail("用户名已存在");
        }
        user.setCreated(new Date());
        user.setStatus(1);
        user.setDeleted((byte) 0);
        if(StringUtils.isEmpty(user.getAvatar())) {
            user.setAvatar(StringConstant.USER_INIT_AVATAR);
        }
        user.setPassword(StringConstant.USER_INIT_PASSWORD);
        user.setIsAdmin(UserType.USER);
        user.setSex(SexEnum.MAN);
        int i = userService.addUser(user);
        return Result.success(MapUtil.builder()
                .put("id",user.getId())
                .put("username",user.getUsername())
                .put("password", "123456")
                .map());
    }

    @RequiresAuthentication
    @GetMapping(value = "/changePassword")
    public Result changePassword(QueryUserParamDto queryUserParamDto) {
        String oldPass = queryUserParamDto.getOldPassword();
        String newPass = queryUserParamDto.getNewPassword();
        Long userId = queryUserParamDto.getUserId();
        User user = userService.getUserById(userId);
        String oldPassword = user.getPassword();
        //SecureUtil
        if(Md5Util.checkPassword(oldPass,oldPassword)) {
            //用户输入的原密码和数据库中密码匹配
            user.setPassword(Md5Util.MD5Encode(newPass));
            int i = userService.updateByPrimaryKeySelective(user);
            if(i>0) {
                //密码修改成功
                return Result.success(user);
            }
        }
        return Result.fail("修改密码失败，请检查原密码是否正确");
    }
}
