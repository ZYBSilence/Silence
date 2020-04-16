package cn.graduation.bbs.controller.menu;

import cn.graduation.bbs.utils.EmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @desc: 后台管理页面跳转的控制层
 * @author: zyb
 * @since: 2020/2/25 11:00
 */
@Slf4j
@Controller
@RequestMapping("/api/admin")
public class AdminMenuController {

    private static final String PREFIX = "/admin/";

    @GetMapping("/index")
    public String index() {
        log.info("论坛后台管理首页");
        return PREFIX + "index";
    }

    @GetMapping("/user")
    public String user() {
        log.info("网站用户");
        return PREFIX + "user_list";
    }

    @GetMapping("/role")
    public String role() {
        log.info("角色管理");
        return PREFIX + "role_list";
    }

    @GetMapping("/post")
    public String post() {
        log.info("帖子列表");
        return PREFIX + "post_list";
    }

    @GetMapping("/post/recommend")
    public String postRecommend() {
        log.info("推送列表");
        return PREFIX + "post_recommend_list";
    }

    @GetMapping("/post/quality")
    public String postQuality() {
        log.info("帖子管理");
        return PREFIX + "post_quality_list";
    }

    @GetMapping("/post/banned")
    public String postBanned() {
        log.info("帖子封禁列表");
        return PREFIX + "post_banned_list";
    }

    @GetMapping("/postType")
    public String postType() {
        log.info("帖子类型列表");
        return PREFIX + "post_type_list";
    }

    @GetMapping("/comment/quality")
    public String commentQuality() {
        log.info("评论管理");
        return PREFIX + "comment_quality_list";
    }

    @GetMapping("/user/quality")
    public String userQuality() {
        log.info("用户封禁");
        return PREFIX + "user_quality_list";
    }

    @GetMapping("/user/banned")
    public String userBanned() {
        log.info("用户封禁列表");
        return PREFIX + "user_banned_list";
    }

    @GetMapping("/back")
    public String back() {
        log.info("背景");
        return PREFIX + "background";
    }

    @RequestMapping("/user/edit")
    public String userEdit(@RequestParam(value = "id", required = false) Integer id) {
        if (EmptyUtils.isEmpty(id) || id == 0) {
            log.info("新增用户");
            return PREFIX + "user_edit";
        }
        log.info("修改用户信息");
        return PREFIX + "user_edit";
    }

    @RequestMapping("/post/detail")
    public String postDetail(@RequestParam(value = "id", required = false) Integer id) {
        log.info("查看id=" + id + "的帖子详情");
        return PREFIX + "post_detail";
    }

    @RequestMapping("/postType/edit")
    public String postTypeEdit(@RequestParam(value = "id", required = false) Integer id) {
        if (EmptyUtils.isEmpty(id) || id == 0) {
            log.info("新增帖子类型");
            return PREFIX + "post_type_edit";
        }
        log.info("修改帖子类型");
        return PREFIX + "post_type_edit";
    }

    @RequestMapping("/comment/detail")
    public String commentDetail(@RequestParam(value = "id", required = false) Integer id) {
        log.info("查看id=" + id + "的评论详情");
        return PREFIX + "comment_detail";
    }
}
