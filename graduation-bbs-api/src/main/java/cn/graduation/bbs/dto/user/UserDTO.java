package cn.graduation.bbs.dto.user;

import lombok.Data;

import java.util.Date;

/**
 * @desc: 业务层传递到持久层的参数
 * @author: zyb
 * @since: 2020/2/21 9:13
 */
@Data
public class UserDTO {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 封禁标记（当标为1时表示用户为封禁状态）
     */
    private Integer delFlag;
    /**
     * 用户封禁时间
     */
    private Date addBannedTime;
}
