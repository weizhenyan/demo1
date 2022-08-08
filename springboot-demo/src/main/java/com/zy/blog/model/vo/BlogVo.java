package com.zy.blog.model.vo;

import com.zy.blog.model.Blog;
import lombok.Data;

import java.io.Serializable;
@Data
public class BlogVo extends Blog implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
