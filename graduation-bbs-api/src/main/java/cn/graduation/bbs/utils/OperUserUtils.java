package cn.graduation.bbs.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @desc: 获取当前登录用户信息的工具类
 * @author: zyb
 * @since: 2020/3/31 14:47
 */
public class OperUserUtils {

    public OperUserUtils() {
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public static MyUserDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        } else {
            Object principal = authentication.getPrincipal();
            return principal != null && principal instanceof MyUserDetails ? (MyUserDetails) principal : null;
        }

    }

    /**
     * 获取当前登陆用户id
     *
     * @return
     */
    public static Integer getUserId() {
        MyUserDetails user = getUser();
        return user == null ? null : user.getId();
    }

    /**
     * 获取当前登录用户名
     *
     * @return
     */
    public static String getUsername() {
        MyUserDetails user = getUser();
        return user == null ? null : user.getUsername();
    }

    /**
     * 获取当前登录用户昵称
     *
     * @return
     */
    public static String getNickname() {
        MyUserDetails user = getUser();
        return user == null ? null : user.getNickname();
    }
}