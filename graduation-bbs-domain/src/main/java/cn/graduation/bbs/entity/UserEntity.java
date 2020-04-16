package cn.graduation.bbs.entity;

import lombok.Data;

import java.util.Date;

/**
 * @desc: 用户表
 * @author: zyb
 * @since: 2019/12/31 16:45
 */
@Data
public class UserEntity {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别（0男、1女）
     */
    private Integer gender;
    /**
     * 头像
     */
    private String photo;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 封禁标记（当标为1时表示用户为封禁状态）
     */
    private Integer delFlag;
    /**
     * 用户封禁时间
     */
    private Date addBannedTime;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;
}
