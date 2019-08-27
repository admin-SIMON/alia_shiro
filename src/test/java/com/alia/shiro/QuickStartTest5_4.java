package com.alia.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class QuickStartTest5_4 {
    private CustomRealm customRealm = new CustomRealm();

    private DefaultSecurityManager securityManager = new DefaultSecurityManager();

    @Before
    public void init() {
        // 构造环境
        securityManager.setRealm(customRealm);
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void testAuthentication() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken("simon", "simon");

        subject.login(usernamePasswordToken);

        System.out.println("认证 : " + subject.isAuthenticated());

        System.out.println("getPrincipal : " + subject.getPrincipal());

        System.out.println("是否是root 权限 : " + subject.hasRole("root"));

        System.out.println("是否有video:delete 权限 : " + subject.isPermitted("video:delete"));
    }

}
