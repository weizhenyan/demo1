package com.zy.blog.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zy.blog.common.enumhandler.EnumSexHandler;
import com.zy.blog.common.enumhandler.EnumTypeHandler;
import com.zy.blog.model.domain.SexEnum;
import com.zy.blog.model.domain.UserType;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;

/**
 * t_user
 * @author 
 */
@Data
@TableName(value = "t_user")
public class User implements Serializable {
    private Long id;

    private String username;

    private String password;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别'0'男'1'女
     */
    @TableField(value = "sex",jdbcType = JdbcType.OTHER,typeHandler = EnumSexHandler.class)
    private SexEnum sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 住址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 最后修改时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    private Integer status;

    private Byte deleted;

    /**
     * '1'是管理员
     */
    @TableField(value = "isAdmin",jdbcType = JdbcType.OTHER,typeHandler = EnumTypeHandler.class)
    private UserType isAdmin;

    private static final long serialVersionUID = 1L;

}