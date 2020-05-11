package cn.graduation.bbs.bean;

import lombok.Data;

import java.util.Date;

/**
 * @desc: 用户收藏帖子信息
 * @author: zyb
 * @since: 2020/5/9 17:40
 */
@Data
public class UserPostCollectDO {
    /**
     * 用户收藏帖子表id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 帖子id
     */
    private Integer postId;
    /**
     * 添加收藏时间
     */
    private Date createTime;
    /**
     * 帖子标题
     */
    private String title;
}
