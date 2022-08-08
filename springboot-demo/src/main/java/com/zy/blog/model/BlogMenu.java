package com.zy.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_blog_menu
 * @author 
 */
@Data
public class BlogMenu implements Serializable {
    private Long id;

    /**
     * 父菜单ID
     */
    private Long parent;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改人1
     */
    private Long update;

    /**
     * 修改时间
     */
    private Date updatetime;

    private Byte status;

    private static final long serialVersionUID = 1L;
}