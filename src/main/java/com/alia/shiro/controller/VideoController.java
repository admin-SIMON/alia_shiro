package com.alia.shiro.controller;

import com.alia.shiro.utils.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Simon
 */
@RestController
@RequestMapping("video")
public class VideoController {

    /**
     * 视频更新
     *
     * @return
     */
    @RequestMapping("/update")
    public JsonData updateVideo() {
        return JsonData.buildSuccess("video 更新成功");
    }
}
