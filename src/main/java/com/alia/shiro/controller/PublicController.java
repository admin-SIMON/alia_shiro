package com.alia.shiro.controller;

import com.alia.shiro.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Simon
 */
@RestController
@RequestMapping("/pub")
public class PublicController {
    @Resource
    private UserService userService;

    @RequestMapping("/find_user_info")
    public Object findUserInfo(@RequestParam("username") String username) {
        return userService.findAllUserInfoByUsername(username);
    }
}
