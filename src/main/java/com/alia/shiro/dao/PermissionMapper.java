package com.alia.shiro.dao;

import com.alia.shiro.domain.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Simon
 */
public interface PermissionMapper {
    /**
     * 根据角色查询权限
     *
     * @param roleId
     * @return
     */
    @Select("select p.* from role_permission rp " +
            "left join permission p on rp.permission_id = p.id " +
            "where rp.role_id=#{roleId}")
    List<Permission> findPermissionListByRoleId(@Param("roleId") int roleId);
}
