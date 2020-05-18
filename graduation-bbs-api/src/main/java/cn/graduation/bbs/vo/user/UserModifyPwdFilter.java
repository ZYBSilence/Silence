package cn.graduation.bbs.vo.user;

import lombok.Data;

/**
 * @desc: 用户修改密码
 * @author: zyb
 * @since: 2020/5/12 10:33
 */
@Data
public class UserModifyPwdFilter {
    /**
     * 当前密码
     */
    private String nowPassword;
    /**
     * 新密码
     */
    private String newPassword;
    /**
     * 确认新密码
     */
    private String reNewPassword;
}
