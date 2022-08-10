package com.wzy.demo.service.manager.web;

import com.wzy.demo.service.api.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @belongProject:wzy-demo
 * @belongsPackage:com.wzy.demo.service.manager.web.es
 * @author:wzy
 * @createTime:2022-08-09 17:05
 * @description:TODO
 * @version:1.0
 */
@Slf4j
@RestController
@RefreshScope
@RequestMapping("/demo")
@Api(tags = "测试nacos")
public class DemoController {

//    @Value("${spring.datasource.name}")
    @Value(value="${server.servlet.context-path}")
    private String name;

    @GetMapping("/user")
    @ApiOperation(value = "测试获取nacos", httpMethod = "GET")
    public Result test() {
        System.out.println("name:"+name);
        return Result.success("用户信息"+name);
    }
}
