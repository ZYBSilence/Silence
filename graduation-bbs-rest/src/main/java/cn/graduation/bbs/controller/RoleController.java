package cn.graduation.bbs.controller;

import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc: 角色管理
 * @author: zyb
 * @since: 2020/2/25 9:39
 */
@Slf4j
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/list")
    public WebResponse queryRoleList() {
        log.info("查询角色信息 queryRoleList 请求参数 {}");
        return roleService.queryRoleList();
    }
}
