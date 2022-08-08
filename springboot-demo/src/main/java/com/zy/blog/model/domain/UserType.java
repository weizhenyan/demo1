package com.zy.blog.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Classname UserType
 * @Description TODO
 * @Date 2021/9/23 0:09
 * @Created by 飞燕
 */
public enum UserType {
    /**管理员*/

    ADMIN(1),

    USER(0);

    @EnumValue
    private int type;

    UserType(int type) {
        this.type=type;
    }

    @JsonValue
    public int getType() {
        return type;
    }
}
