package com.alia.shiro.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * logout, 映射shiro自带过滤器(LogoutFilter)
 * 写退出的接口也没效果, 被过滤器拦截处理
 * 并重定向到需要登录的接口(如果不是前后端分离,则跳转页面)
 *
 * @author Simon
 */
@RestController
public class LogoutController {

}
