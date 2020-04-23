package cn.graduation.bbs.entity;

import lombok.Data;

import java.util.Date;

/**
 * @desc: 帖子表
 * @author: zyb
 * @since: 2020/3/31 18:33
 */
@Data
public class PostEntity {
    /**
     * 帖子id
     */
    private Integer id;
    /**
     * 帖子标题
     */
    private String title;
    /**
     * 帖子内容
     */
    private String content;
    /**
     * 所属用户id
     */
    private Integer userId;
    /**
     * 帖子类型id
     */
    private Integer postTypeId;
    /**
     * 是否为推荐（0-否，1-是）
     */
    private Integer recommend;
    /**
     * 状态（0-正常，1-封禁）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 添加推荐时间
     */
    private Date addRecommendTime;
    /**
     * 帖子封禁时间
     */
    private Date addBannedTime;
    /**
     * 帖子类型
     */
    private String postType;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String photo;
}
