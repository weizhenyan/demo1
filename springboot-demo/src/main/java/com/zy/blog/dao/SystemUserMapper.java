package com.zy.blog.dao;

import com.zy.blog.model.SystemUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface SystemUserMapper {
    SystemUser findUserByName(@Param("userAccount") String name);
}