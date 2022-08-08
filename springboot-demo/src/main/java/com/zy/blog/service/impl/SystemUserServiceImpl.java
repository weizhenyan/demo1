package com.zy.blog.service.impl;

import com.zy.blog.dao.SystemUserMapper;
import com.zy.blog.model.SystemUser;
import com.zy.blog.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    private SystemUserMapper userMapper;

    @Override
    public SystemUser findUserByName(String name) {
        return userMapper.findUserByName(name);
    }
}