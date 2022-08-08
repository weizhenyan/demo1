package com.zy.blog.web;

import com.zy.blog.common.lang.Result;
import com.zy.blog.model.SystemUser;
import com.zy.blog.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysUser")
public class SystemUserController {
    @Autowired
    SystemUserService systemUserService;
    @GetMapping("/userdata")
    public Object userData() {
        SystemUser systemUser = systemUserService.findUserByName("zy");
        return Result.success(systemUser);
    }
}
