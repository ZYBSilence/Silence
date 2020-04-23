package cn.graduation.bbs.bean;

import lombok.Data;

/**
 * @desc: 用户id和对应的评论数量
 * @author: zyb
 * @since: 2020/4/22 21:56
 */
@Data
public class UserCommentCountDO {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 评论数量
     */
    private Integer commentCount;
}
