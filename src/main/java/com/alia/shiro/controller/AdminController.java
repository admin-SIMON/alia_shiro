package com.alia.shiro.controller;

import com.alia.shiro.utils.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Simon
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    /**
     * 查询订单收益
     *
     * @return
     */
    @RequestMapping("/video/order")
    public JsonData findMyOrder() {
        Map<Object, Object> recordMap = new HashMap<>();
        recordMap.put("微服务SpringCloud+Docker入门到高级实战", "477元");
        recordMap.put("互联网架构多线程并发编程高级教程", "1000元");

        return JsonData.buildSuccess(recordMap);
    }
}
