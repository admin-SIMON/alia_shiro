package com.alia.shiro.service.impl;

import com.alia.shiro.dao.RoleMapper;
import com.alia.shiro.dao.UserMapper;
import com.alia.shiro.domain.Role;
import com.alia.shiro.domain.User;
import com.alia.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Simon
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public User findAllUserInfoByUsername(String username) {
        User user = userMapper.findByUserName(username);

        // 用户的角色集合
        if (null != user) {
            List<Role> roleList = roleMapper.findListByUserId(user.getId());
            user.setRoleList(roleList);
        }

        return user;
    }

    @Override
    public User findSimpleUserInfoById(int userId) {
        return userMapper.findById(userId);
    }

    @Override
    public User findSimpleUserInfoByUsername(String username) {
        return userMapper.findByUserName(username);
    }
}
