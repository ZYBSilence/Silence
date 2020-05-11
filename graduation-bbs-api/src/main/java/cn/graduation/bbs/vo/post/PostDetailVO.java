package cn.graduation.bbs.vo.post;

import lombok.Data;

import java.util.Date;

/**
 * @desc: 社区帖子详情
 * @author: zyb
 * @since: 2020/4/25 0:08
 */
@Data
public class PostDetailVO {
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
    /**
     * 帖子的评论数量
     */
    private Integer commentCount;
    /**
     * 帖子收藏状态（1-已收藏，0-未收藏）
     */
    private Integer collect;
    /**
     * 帖子点赞状态（1-已点赞，0-未点赞）
     */
    private Integer postTags;
    /**
     * 帖子收藏数量
     */
    private Integer collectCount;
    /**
     * 帖子点赞数量
     */
    private Integer postTagsCount;
}
