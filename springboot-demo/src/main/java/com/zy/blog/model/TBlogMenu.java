package com.zy.blog.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_blog_menu
 * @author 
 */
@ApiModel(value="com.zy.blog.model.TBlogMenu")
@Data
public class TBlogMenu implements Serializable {
    private Long id;

    /**
     * 父菜单ID
     */
    @ApiModelProperty(value="父菜单ID")
    private Long parent;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value="菜单名称")
    private String name;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人")
    private Long creator;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createtime;

    /**
     * 修改人
     */
    @ApiModelProperty(value="修改人")
    private Long update;

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    private Date updatetime;

    private Byte status;

    private static final long serialVersionUID = 1L;
}