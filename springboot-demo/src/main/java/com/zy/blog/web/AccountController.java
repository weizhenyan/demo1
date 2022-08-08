package com.zy.blog.web;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.zy.blog.common.dto.LoginDto;
import com.zy.blog.common.lang.Result;
import com.zy.blog.model.User;
import com.zy.blog.service.UserService;
import com.zy.blog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
//用户
@RestController
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;
    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getUserByName(loginDto.getUsername());
        Assert.notNull(user,"用户不存在");
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码不正确");
        }
        if("0".equals(user.getStatus())) {
            return Result.fail("该账户已锁定");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization",jwt);
        //允许前端跨域请求
        response.setHeader("Access-Control-Expose-Headers","Authorization");
        return Result.success(MapUtil.builder()
                .put("id",user.getId())
                .put("username",user.getUsername())
                .put("avatar",user.getAvatar())
                .put("email",user.getEmail())
                .put("isAdmin",user.getIsAdmin())
                .map());
    }
    @CrossOrigin
    @RequiresAuthentication
    @RequestMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success(null);
    }
    @CrossOrigin
    @RequiresAuthentication
    @RequestMapping("/userLogout")
    public Result userLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success(null);
    }


}
