package cn.graduation.bbs.vo.user;

import lombok.Data;

import java.util.Date;

/**
 * @desc: 查询网站用户服务端返回到前端的参数
 * @author: zyb
 * @since: 2020/1/10 15:24
 */
@Data
public class UserVO {
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
     * 性别（0男、1女）
     */
    private Integer gender;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;
    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 头像
     */
    private String photo;
    /**
     * 封禁标记（当标为1时表示用户为封禁状态）
     */
    private Integer delFlag;
    /**
     * 用户封禁时间
     */
    private Date addBannedTime;
}
