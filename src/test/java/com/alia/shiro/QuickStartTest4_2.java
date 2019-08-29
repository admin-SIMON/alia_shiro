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
 * @Description 登录权限验证模板
 * @Date 17:00 2019/7/25
 * @Param
 * @return
 **/
public class QuickStartTest4_2 {
    private SimpleAccountRealm accountRealm = new SimpleAccountRealm();
    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    @Before
    public void init() {

        // 初始化数据源
        accountRealm.addAccount("admin", "admin");
        accountRealm.addAccount("simon", "simon");

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
                new UsernamePasswordToken("admin", "admin");

        subject.login(usernamePasswordToken);

        System.out.println("认证结果 : " + subject.isAuthenticated());
    }
}
