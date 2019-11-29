package com.alia.shiro.controller;

import com.alia.shiro.domain.UserQuery;
import com.alia.shiro.service.UserService;
import com.alia.shiro.utils.JsonData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Simon
 */
@RestController
@RequestMapping("pub")
public class PublicController {
    @Resource
    private UserService userService;

    @PostMapping("login")
    public JsonData login(@RequestBody UserQuery userQuery, HttpServletRequest request, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> info = new HashMap<>();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userQuery.getName(), userQuery.getPwd());

            subject.login(usernamePasswordToken);

            info.put("msg", "登录成功");
            info.put("session_id", subject.getSession().getId());

            return JsonData.buildSuccess(info);
        } catch (Exception e) {
            e.printStackTrace();
            // todo 处理安全, 防止暴力破解


            return JsonData.buildError("账号或者密码错误");
        }
    }

    /**
     * 需要登录却没登录,则调用此接口
     *
     * @return
     */
    @RequestMapping("need_login")
    public JsonData needLogin() {
        return JsonData.buildError("/pub/login", "温馨提示: 请求未授权, 请登录", JsonData.Code.REQUEST_UNAUTHORIZED);
    }

    /**
     * 未授权就会调用此方法
     *
     * @return
     */
    @RequestMapping("not_permit")
    public JsonData notPermit() {
        return JsonData.buildError("温馨提示: 拒绝访问", JsonData.Code.NO_ACCESS);
    }

    @RequestMapping("index")
    public JsonData index() {
        List<String> videoList = new ArrayList<>();
        videoList.add("SpringBoot 2.x微信支付在线教育网站项目实战");
        videoList.add("微服务SpringCloud+Docker入门到高级实战");
        videoList.add("互联网架构多线程并发编程高级教程");
        videoList.add("价值198元新版本RocketMQ4.X教程消息队列");
        videoList.add("Zookeeper+Dubbo视频教程 微服务教程分布式");

        return JsonData.buildSuccess(videoList);
    }
}
