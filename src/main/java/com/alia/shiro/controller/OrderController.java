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
@RequestMapping("authc")
public class OrderController {

    /**
     * 查询用户的播放记录
     *
     * @return
     */
    @RequestMapping("/video/play_record")
    public JsonData findMyPlayRecord() {
        Map<Object, Object> recordMap = new HashMap<>();
        recordMap.put("微服务SpringCloud+Docker入门到高级实战", "第3章第1集");
        recordMap.put("互联网架构多线程并发编程高级教程", "第7章第4集");

        return JsonData.buildSuccess(recordMap);
    }
}
