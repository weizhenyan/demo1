package com.zy.blog.mybatisplus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MybatisPlusSqlInjector extends AbstractSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        return Stream.of(
                new Insert(),
                new Delete(),
                new DeleteById(),
                new Update(),
                new UpdateById(),
                new SelectById(),
                new SelectCount(),
                new SelectOne(),
                new SelectObjs(),
                new SelectList(),
                new SelectPage(),
                new SelectMapsPage()
        ).collect(Collectors.toList());
    }
}
