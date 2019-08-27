package com.alia.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
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
public class QuickStartTest5_3 {
    /**
     * @return void
     * @Description 读取配置文件ini方式连接
     * @Author Simon
     * @Date 14:21 2019/8/22
     * @Param []
     **/
    @Test
    public void testAuthentication() {
        // 创建SecurityManager工厂,通过配置文件ini创建
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:jdbcrealm.ini");

        SecurityManager securityManager = factory.getInstance();

        // 将securityManager 设置到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        // 用户输入的账号密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin");

        subject.login(usernamePasswordToken);

        System.out.println("认证结果 : " + subject.isAuthenticated());

        System.out.println("是否有对应的admin权限 : " + subject.hasRole("admin"));

        System.out.println("是否有video:find权限 : " + subject.isPermitted("video:find"));

        // subject.checkPermission("video:find");

        Object principal = subject.getPrincipal();
        System.out.println("getPrincipal : " + principal);

        subject.logout();

        System.out.println("logout后认证结果 : " + subject.isAuthenticated());
    }

    /**
     * @return void
     * @Description 通过对象配置读取数据库
     * @Author Simon
     * @Date 14:21 2019/8/22
     * @Param []
     **/
    @Test
    public void test2() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://112.74.34.220:3306/shiro_jdbcrealm?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false");
        ds.setUsername("root");
        ds.setPassword("Simon!!2019");

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setPermissionsLookupEnabled(true);
        jdbcRealm.setDataSource(ds);

        securityManager.setRealm(jdbcRealm);

        // ↓↓↓重复代码↓↓↓
        // 将securityManager 设置到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        // 用户输入的账号密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "admin");

        subject.login(usernamePasswordToken);

        System.out.println("认证结果 : " + subject.isAuthenticated());

        System.out.println("是否有对应的admin权限 : " + subject.hasRole("admin"));

        System.out.println("是否有video:find权限 : " + subject.isPermitted("video:find"));

        // subject.checkPermission("video:find");

        Object principal = subject.getPrincipal();
        System.out.println("getPrincipal : " + principal);

        subject.logout();

        System.out.println("logout后认证结果 : " + subject.isAuthenticated());
    }
}
