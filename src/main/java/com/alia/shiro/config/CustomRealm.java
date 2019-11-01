package com.alia.shiro.config;

import com.alia.shiro.domain.Permission;
import com.alia.shiro.domain.Role;
import com.alia.shiro.domain.User;
import com.alia.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义realm
 *
 * @author Simon
 */
public class CustomRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    /**
     * 获取授权信息
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权 : doGetAuthorizationInfo");
        String username = (String) principals.getPrimaryPrincipal();
        User user = userService.findAllUserInfoByUsername(username);

        List<Role> roleList = user.getRoleList();

        List<String> stringPermissionList = new ArrayList<>();
        List<String> stringRoleList = roleList.stream().map(
                obj -> {
                    List<Permission> permissionList = obj.getPermissionList();
                    for (Permission p : permissionList) {
                        if (null != p) {
                            stringPermissionList.add(p.getName());
                        }
                    }

                    return obj.getName();
                }
        ).collect(Collectors.toList());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);

        return simpleAuthorizationInfo;
    }

    /**
     * 获取用户登录认证信息
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证 : doGetAuthenticationInfo");

        String username = (String) token.getPrincipal();
        User user = userService.findSimpleUserInfoByUsername(username);

        String password = user.getPassword();

        if (null == password || "".equals(password)) {
            return null;
        }

        return new SimpleAuthenticationInfo(username, user.getPassword(), this.getClass().getName());
    }
}
