package com.alia.shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.apache.commons.lang.time.DateUtils.MILLIS_PER_MINUTE;

/**
 * Shiro配置
 *
 * @author Simon
 */
@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        System.out.println("执行 ShiroConfig.shiroFilter()");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 需要登录的接口,如果访问某个接口,需要登录却没登录,则调用此接口(如果不是前后端分离,则跳转页面)
        shiroFilterFactoryBean.setLoginUrl("/pub/need_login");

        // 登录成功,跳转URL,如果前后端分离,则没这个调用
        shiroFilterFactoryBean.setSuccessUrl("/");

        // 没有权限,未授权就会调用此方法, 验证登录->再验证是否有权限
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/not_permit");

        // 拦截器路径,使用有序LinkedHashMap(坑,会导致拦截顺序不一样,效果时有时无)
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 退出过滤器
        filterChainDefinitionMap.put("/logout", "logout");
        // 匿名(游客)过滤器(DefaultFilter 里面权限 ->anon 可以匿名访问)
        filterChainDefinitionMap.put("/pub/**", "anon");
        // 登录用户才可以访问(DefaultFilter 里面权限 ->authc 通过登录认证才能访问)
        filterChainDefinitionMap.put("/authc/**", "authc");
        // 有管理员角色才可以访问
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        // 有编辑权限才可以访问
        filterChainDefinitionMap.put("/video/update", "perms[video_update]");

        // 坑,过滤链顺序执行:一般 /** 放最下面(DefaultFilter 里面权限 ->authc 通过登录认证才能访问)
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 如果不是前后端分离, 则不必设置下面的 sessionManager
        securityManager.setSessionManager(sessionManager());

        // 设置 Realm (推荐放到最后, 不然某些情况可能不生效)
        securityManager.setRealm(customRealm());

        return securityManager;
    }

    /**
     * 自定义 Realm
     *
     * @return
     */
    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();

        // TODO 如果数据是明文, 双重MD5会验证错误

        // customRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        return customRealm;
    }

    /**
     * 密码加解密规则
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        // 设置散列算法 : MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        // 双重MD5 散列次数 2 : 相当于 MD5(MD5(XXX))
        credentialsMatcher.setHashIterations(2);

        return credentialsMatcher;
    }

    /**
     * 自定义 sessionManager
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();

        // 超时时间, 默认30分钟(续), 会话超时: 方法里面单位是毫秒(当前配置一分钟)
        customSessionManager.setGlobalSessionTimeout(MILLIS_PER_MINUTE);
        return customSessionManager;
    }
}
