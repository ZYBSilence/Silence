package cn.graduation.bbs.bean;

import lombok.Data;

/**
 * @desc: 帖子id和对应的评论数量
 * @author: zyb
 * @since: 2020/4/23 15:20
 */
@Data
public class PostCommentCountDO {
    /**
     * 帖子id
     */
    private Integer postId;
    /**
     * 评论数量
     */
    private Integer commentCount;
}
