package com.zy.blog.web.ab;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Unsafe;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.stream.Collectors;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2022/5/23 14:08
 * @Created by 飞燕
 */
@RestController
@RequestMapping(value = "/test")
@Api(tags = "test口")
public class Test {

    @GetMapping("/test")
    @ApiOperation(value = "新增/修改", httpMethod = "GET")
    public void test(){
        ThreadLocal threadLocal=new ThreadLocal();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        new Thread(()->{

        }).start();
        threadLocal.remove();

        List a = new ArrayList();
        Set s = new HashSet();
        s.addAll(a);

        a.stream().distinct().collect(Collectors.toList());
//        Hashtable
        threadLocal=null;
//        AbstractQueuedSynchronizer
//        HashSet
        Unsafe
    }

}
