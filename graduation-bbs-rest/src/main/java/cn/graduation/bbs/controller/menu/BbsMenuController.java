package cn.graduation.bbs.controller.menu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @desc: 论坛页面跳转的控制层
 * @author: zyb
 * @since: 2020/2/25 14:22
 */
@Slf4j
@Controller
@RequestMapping("/api/bbs")
public class BbsMenuController {
    private static final String USER = "/user/";
    private static final String POST = "/post/";

    @GetMapping("/index")
    public String index() {
        log.info("论坛首页");
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        log.info("论坛登录页");
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        log.info("论坛用户注册");
        return "register";
    }

    @GetMapping("/user/index")
    public String userIndex() {
        log.info("用户中心");
        return USER + "index";
    }

    @GetMapping("/user/home")
    public String userHome() {
        log.info("我的主页");
        return USER + "home";
    }

    @GetMapping("/user/set")
    public String userSet() {
        log.info("基本设置");
        return USER + "set";
    }

    @GetMapping("/user/message")
    public String userMessage() {
        log.info("我的消息");
        return USER + "message";
    }

    @GetMapping("/post/index")
    public String postIndex() {
        log.info("帖子列表");
        return POST + "index";
    }

    @GetMapping("/post/add")
    public String postAdd() {
        log.info("编辑帖子");
        return POST + "add";
    }

    @GetMapping("/post/detail")
    public String postDetail() {
        log.info("帖子详情");
        return POST + "detail";
    }
}
