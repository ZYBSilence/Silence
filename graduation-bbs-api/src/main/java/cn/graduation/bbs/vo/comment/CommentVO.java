package cn.graduation.bbs.vo.comment;

import lombok.Data;

import java.util.Date;

/**
 * @desc: 查询评论列表服务端返回到前端的参数
 * @author: zyb
 * @since: 2020/4/8 17:19
 */
@Data
public class CommentVO {
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
     * 用户头像
     */
    private String photo;
    /**
     * 评论点赞标记
     */
    private Integer commentTags;
    /**
     * 评论点赞数量
     */
    private Integer commentTagsCount;
}
