package com.zy.blog.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;
@ExcelTarget("blog")
@TableName(value = "t_blog")
public class Blog  implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.id
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.user_id
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
//    @JsonSerialize(using = LongJsonSerializer.class)
//    @JsonDeserialize(using = LongJsonDeserializer.class )
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.title
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    @Excel(name = "博客标题", width = 30, isImportField = "true_st")
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.description
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.created
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    @Excel(name = "创建时间", databaseFormat = "yyyy-MM-dd HH:mm:ss", format = "yyyy-MM-dd", isImportField = "true_st", width = 20)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.status
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */

    private int status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog.content
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.id
     *
     * @return the value of t_blog.id
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.id
     *
     * @param id the value for t_blog.id
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.user_id
     *
     * @return the value of t_blog.user_id
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.user_id
     *
     * @param userId the value for t_blog.user_id
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.title
     *
     * @return the value of t_blog.title
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.title
     *
     * @param title the value for t_blog.title
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.description
     *
     * @return the value of t_blog.description
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.description
     *
     * @param description the value for t_blog.description
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.created
     *
     * @return the value of t_blog.created
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.created
     *
     * @param created the value for t_blog.created
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.status
     *
     * @return the value of t_blog.status
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public int getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.status
     *
     * @param status the value for t_blog.status
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog.content
     *
     * @return the value of t_blog.content
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog.content
     *
     * @param content the value for t_blog.content
     *
     * @mbg.generated Sun Jun 20 02:25:20 GMT+08:00 2021
     */
    public void setContent(String content) {
        this.content = content;
    }


}