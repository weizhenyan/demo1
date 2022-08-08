package com.zy.blog.dto.user;

import com.zy.blog.common.dto.PageDto;
import lombok.Data;

import java.util.Date;

/**
 * @Classname QueryUserParamDto
 * @Description TODO 查询用户数据的入参
 * @Date 2021/9/5 16:08
 * @Created by 飞燕
 */
@Data
public class QueryUserParamDto extends PageDto {
    private Long userId;

    private String username;

    private Integer status;

    private Object sex;

    private Date created;

    private String newPassword;

    private String oldPassword;
}
