package com.alia.shiro.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Simon
 */
@RestController
@RequestMapping("/api/admin/user")
public class UserController {
    /**
     * // @RequiresPermissions(value = {"user:add", "user:del"}, logical = Logical.AND)
     *
     * @return
     */
    @RequiresRoles(value = {"admin", "editor"}, logical = Logical.OR)
    @RequestMapping("/list_user")
    public Object listUser() {
        return null;
    }
}
