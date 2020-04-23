package cn.graduation.bbs.vo.user;

import lombok.Data;

/**
 * @desc: 用户信息和对应的评论数量
 * @author: zyb
 * @since: 2020/4/22 21:58
 */
@Data
public class UserCommentCountVO {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String photo;
    /**
     * 评论数量
     */
    private Integer commentCount;
}
