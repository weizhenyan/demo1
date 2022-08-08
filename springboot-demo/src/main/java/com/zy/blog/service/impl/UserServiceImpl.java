package com.zy.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.blog.dao.UserMapper;
import com.zy.blog.dto.user.QueryUserParamDto;
import com.zy.blog.model.User;
import com.zy.blog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteUserById(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int addUser(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.selectUser(userName);
    }

    @Override
    public List<User> getUserByParam(QueryUserParamDto queryUserParamDto) {
        return userMapper.selectUserByParam(queryUserParamDto);
    }
    @Override
    public IPage<User> getAll(Page page, QueryWrapper queryWrapper) {
        return userMapper.findByPage(page,queryWrapper);
    }
}
