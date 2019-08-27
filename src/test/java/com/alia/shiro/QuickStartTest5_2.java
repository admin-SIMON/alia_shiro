package com.alia.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @Author Simon
 * @Description //TODO
 * @Date 17:00 2019/7/25
 * @Param
 * @return
 **/
public class QuickStartTest5_2 {

    @Test
    public void testAuthentication() {
        // 创建SecurityManager工厂,通过配置文件ini创建
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        SecurityManager securityManager = factory.getInstance();

        // 将securityManager 设置到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        // 用户输入的账号密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("simon", "password");

        subject.login(usernamePasswordToken);

        System.out.println("认证结果 : " + subject.isAuthenticated());

        System.out.println("是否有对应的admin权限 : " + subject.hasRole("admin"));

        System.out.println("是否有video:find权限 : " + subject.isPermitted("video:find"));

        subject.checkPermission("video:find");

        Object principal = subject.getPrincipal();
        System.out.println("getPrincipal : " + principal);

        subject.logout();

        System.out.println("logout后认证结果 : " + subject.isAuthenticated());
    }
}
