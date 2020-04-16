package cn.graduation.bbs.vo.post;

import lombok.Data;

import java.util.Date;

/**
 * @desc: 查询帖子列表服务端返回到前端的参数
 * @author: zyb
 * @since: 2020/4/2 15:24
 */
@Data
public class PostVO {
    /**
     * 帖子id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * t帖子标题
     */
    private String title;
    /**
     * 帖子类型
     */
    private String postType;
    /**
     * 是否为推荐（0-否，1-是）
     */
    private Integer recommend;
    /**
     * 状态（0-正常，1-封禁）
     */
    private Integer status;
    /**
     * 发布时间
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
}
