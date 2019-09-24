package com.alia.shiro.dao;

import com.alia.shiro.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Simon
 */
public interface UserMapper {
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象
     */
    @Select("select * from user where username = #{username}")
    User findByUserName(@Param("username") String username);

    /**
     * 根据用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户对象
     */
    @Select("select * from user where id = #{userId}")
    User findById(@Param("userId") int id);

    /**
     * 根据用户名和密码查询用户
     *
     * @param username 用户名
     * @param pwd      密码
     * @return 用户对象
     */
    @Select("select * from user where username = #{username} and password = #{pwd}")
    User findByUsernameAndPwd(@Param("username") String username, @Param("pwd") String pwd);
}
