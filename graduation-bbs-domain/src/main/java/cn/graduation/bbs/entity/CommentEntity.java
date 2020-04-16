package cn.graduation.bbs.entity;

import lombok.Data;

import java.util.Date;

/**
 * @desc: 评论表
 * @author: zyb
 * @since: 2020/3/31 18:33
 */
@Data
public class CommentEntity {
    /**
     * 评论id
     */
    private Integer id;
    /**
     * 所属帖子id
     */
    private Integer postId;
    /**
     * 评论者id
     */
    private Integer userId;
    /**
     * 评论内容
     */
    private String comment;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 帖子标题
     */
    private String title;
    /**
     * 帖子状态
     */
    private Integer status;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户名
     */
    private String username;
    /**
     * 帖子类型
     */
    private String postType;
    /**
     * 帖子内容
     */
    private String postContent;
}
