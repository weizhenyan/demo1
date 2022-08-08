package com.zy.blog.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Classname SexEnum
 * @Description TODO
 * @Date 2021/9/23 20:12
 * @Created by 飞燕
 */
public enum SexEnum implements IEnum<Integer> {

    WOMAN(1),
    /**
     * "男"
     */
    MAN(0);


    @EnumValue
    private int code;

    @JsonValue
    public int getCode() {
        return code;
    }
    SexEnum(int code) {
        this.code=code;
    }

    @Override
    public Integer getValue() {
        return code;
    }
}
