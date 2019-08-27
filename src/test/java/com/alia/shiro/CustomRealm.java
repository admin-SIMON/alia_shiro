package com.alia.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    // 模拟数据库初始化数据
    private final Map<String, String> userInfoMap = new HashMap<>();

    {
        userInfoMap.put("simon", "simon");
        userInfoMap.put("james", "james");
    }

    // role -> permission
    private final Map<String, Set<String>> permissionMap = new HashMap<>();

    {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        set1.add("video:delete");
        set1.add("video:add");

        set2.add("video:find");
        set2.add("video:buy");

        permissionMap.put("simon", set1);
        permissionMap.put("james", set2);
    }

    // user -> role
    private final Map<String, Set<String>> roleMap = new HashMap<>();

    {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        set1.add("root");

        set2.add("role1");
        set2.add("role2");

        roleMap.put("simon", set1);
        roleMap.put("james", set2);
    }

    // 模拟数据库查询
    private String getPwdByUserNameFromDB(String name) {
        return userInfoMap.get(name);
    }

    private Set<String> getRolesByNameFromDB(String name) {
        return roleMap.get(name);
    }

    private Set<String> getPermissionsByNameFromDB(String name) {
        return permissionMap.get(name);
    }

    // 进行权限校验时调用
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("认证 doGetAuthorizationInfo");
        String name = principals.getPrimaryPrincipal().toString();
        Set<String> permissions = getPermissionsByNameFromDB(name);
        Set<String> roles = getRolesByNameFromDB(name);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }


    // 进行用户登录时调用
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证 doGetAuthenticationInfo");

        String name = token.getPrincipal().toString();
        String pwd = getPwdByUserNameFromDB(name);

        if (null == pwd || "".equals(pwd)) {
            return null;
        }

        return new SimpleAuthenticationInfo(name, pwd, this.getName());

    }
}
