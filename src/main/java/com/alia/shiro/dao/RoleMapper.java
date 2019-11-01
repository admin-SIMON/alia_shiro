package com.alia.shiro.dao;

import com.alia.shiro.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author Simon
 */
public interface RoleMapper {
    /**
     * 根据用户查询角色
     *
     * @param userId
     * @return
     */
    @Select("select r.id as id,r.name as name,r.description as des from user_role ur " +
            "left join role r on ur.role_id = r.id " +
            "where ur.user_id=#{userId}")
    @Results(
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(id = true, property = "name", column = "name"),
                    @Result(id = true, property = "description", column = "des"),
                    @Result(id = true, property = "permissionList", column = "id",
                            many = @Many(select = "com.alia.shiro.dao.PermissionMapper.findPermissionListByRoleId",
                                    fetchType = FetchType.DEFAULT)
                    )
            }
    )
    List<Role> findListByUserId(@Param("userId") int userId);
}
