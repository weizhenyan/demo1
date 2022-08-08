package com.zy.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.blog.dto.user.QueryUserParamDto;
import com.zy.blog.model.User;

import java.util.List;

public interface UserService {
    int deleteUserById(Long id);

    int addUser(User record);

    User getUserById(Long id);

    List<User> getUsers();

    int updateByPrimaryKeySelective(User user);

    User getUserByName(String userName);

    List<User>  getUserByParam(QueryUserParamDto queryUserParamDto);

    public IPage<User> getAll(Page<User> page, QueryWrapper<User> queryWrapper);
}
