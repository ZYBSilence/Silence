package cn.graduation.bbs.dto.post;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @desc: 业务层传递到持久层的参数
 * @author: zyb
 * @since: 2020/4/1 14:30
 */
@Data
public class PostDTO {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 帖子标题
     */
    private String title;
    /**
     * 帖子类型id
     */
    private Integer postTypeId;
    /**
     * 帖子类型
     */
    private String postType;
    /**
     * 是否为推荐（0-否，1-是）
     */
    private Integer recommend;
    /**
     * 状态（0-上线，1-下线）
     */
    private Integer status;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 帖子id
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
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
     * id集合
     */
    private List<Integer> ids;
    /**
     * 帖子内容
     */
    private String content;
}
