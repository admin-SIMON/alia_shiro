package com.alia.shiro.service;

import com.alia.shiro.domain.User;

/**
 * @author Simon
 */
public interface UserService {
    /**
     * 获取用户全部信息,包括角色/权限
     *
     * @param username
     * @return
     */
    User findAllUserInfoByUsername(String username);

    /**
     * 获取用户基本信息
     *
     * @param userId
     * @return
     */
    User findSimpleUserInfoById(int userId);

    /**
     * 获取用户基本信息,包括角色/权限
     *
     * @param username
     * @return
     */
    User findSimpleUserInfoByUsername(String username);
}
