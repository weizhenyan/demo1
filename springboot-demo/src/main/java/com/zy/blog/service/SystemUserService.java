package com.zy.blog.service;

import com.zy.blog.model.SystemUser;

public interface SystemUserService {
    public SystemUser findUserByName(String name);
}
