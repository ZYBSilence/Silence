package cn.graduation.bbs.bean;

import lombok.Data;

/**
 * @desc: 用户id和对应的帖子数量
 * @author: zyb
 * @since: 2020/4/22 21:35
 */
@Data
public class UserPostCountDO {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 帖子数量
     */
    private Integer postCount;
}
