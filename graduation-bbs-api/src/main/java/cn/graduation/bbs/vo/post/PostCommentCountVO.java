package cn.graduation.bbs.vo.post;

import lombok.Data;

/**
 * @desc: 帖子信息和对应的评论数量
 * @author: zyb
 * @since: 2020/4/23 15:22
 */
@Data
public class PostCommentCountVO {
    /**
     * 帖子id
     */
    private Integer postId;
    /**
     * 帖子标题
     */
    private String title;
    /**
     * 评论数量
     */
    private Integer commentCount;
}
