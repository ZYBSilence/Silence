package cn.graduation.bbs.controller;


import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.entity.UserEntity;
import cn.graduation.bbs.service.UserService;
import cn.graduation.bbs.vo.user.UserDelFilter;
import cn.graduation.bbs.vo.user.UserFilter;
import cn.graduation.bbs.vo.user.UserModifyPwdFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @desc: 用户管理
 * @author: zyb
 * @since: 2020/1/2 12:08
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    public WebResponse queryUserList(@RequestBody UserFilter userFilter) {
        log.info("根据条件分页查询用户信息 queryUserList 请求参数 {}", userFilter);
        return userService.queryUserList(userFilter);
    }

    @PostMapping("/save")
    public WebResponse save(@RequestBody UserEntity userEntity) {
        log.info("新增用户/注册用户 save 请求参数 {}", userEntity);
        return userService.save(userEntity);
    }

    @PostMapping("/update")
    public WebResponse update(@RequestBody UserEntity userEntity) {
        log.info("更新用户信息 update 请求参数 {}", userEntity);
        return userService.update(userEntity);
    }

    @PostMapping("/delete")
    public WebResponse delete(@RequestBody UserDelFilter userDelFilter) {
        log.info("根据id集合批量删除用户 delete 请求参数 {}", userDelFilter);
        return userService.delete(userDelFilter);
    }

    @PostMapping("/detail")
    public WebResponse queryById(@RequestBody UserEntity userEntity) {
        log.info("根据id查询用户信息 queryById 请求参数 {}", userEntity);
        return userService.queryById(userEntity);
    }

    @PostMapping("/detail/username")
    public WebResponse queryByUsername(@RequestBody UserEntity userEntity) {
        log.info("根据用户名查询用户信息 queryByUsername 请求参数 {}", userEntity);
        return userService.queryByUsername(userEntity);
    }

    @PostMapping("/modify/status")
    public WebResponse modifyDelFlag(@RequestBody UserFilter userFilter) {
        log.info("修改用户的封禁状态 modifyDelFlag 请求参数 {}", userFilter);
        return userService.modifyDelFlag(userFilter);
    }

    @PostMapping("/banned")
    public WebResponse bannedUser(@RequestBody UserFilter userFilter) {
        log.info("批量封禁用户 bannedUser 请求参数 {}", userFilter);
        return userService.bannedUser(userFilter);
    }

    @PostMapping("/unBan")
    public WebResponse unBanPost(@RequestBody UserFilter userFilter) {
        log.info("批量解封用户 unBanUser 请求参数 {}", userFilter);
        return userService.unBanUser(userFilter);
    }

    @PostMapping("/set/message")
    public WebResponse modifyUserMessage(@RequestBody UserFilter userFilter) {
        log.info("修改用户基本信息 modifyUserMessage 请求参数 {}", userFilter);
        return userService.modifyUserMessage(userFilter);
    }

    @PostMapping("/set/photo")
    public WebResponse modifyUserPhoto(@RequestBody UserFilter userFilter) {
        log.info("修改用户头像 modifyUserPhoto 请求参数 {}", userFilter);
        return userService.modifyUserPhoto(userFilter);
    }

    @PostMapping("/set/pwd")
    public WebResponse modifyUserPassword(@RequestBody UserModifyPwdFilter userModifyPwdFilter) {
        log.info("修改用户密码 modifyUserPassword 请求参数 {}", userModifyPwdFilter);
        return userService.modifyUserPassword(userModifyPwdFilter);
    }
}