package com.alia.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author Simon
 * @Description //TODO
 * @Date 17:00 2019/7/25
 * @Param
 * @return
 **/
public class QuickStartTest4_3 {
    private SimpleAccountRealm accountRealm = new SimpleAccountRealm();
    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    @Before
    public void init() {

        // 初始化数据源
        accountRealm.addAccount("admin", "admin", "root", "admin");
        accountRealm.addAccount("simon", "simon", "user");

        // 构建环境
        defaultSecurityManager.setRealm(accountRealm);
    }

    @Test
    public void testAuthentication() {

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 当前操作主体
        Subject subject = SecurityUtils.getSubject();

        // 用户输入的账号密码
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken("simon", "simon");

        subject.login(usernamePasswordToken);

        System.out.println("认证结果 : " + subject.isAuthenticated());

        System.out.println("是否有对应的root权限 : " + subject.hasRole("root"));

        Object principal = subject.getPrincipal();
        System.out.println("getPrincipal : " + principal);

        subject.logout();

        System.out.println("logout后认证结果 : " + subject.isAuthenticated());
    }
}
