package cn.graduation.bbs.dto.comment;

import lombok.Data;

import java.util.Date;

/**
 * @desc: 业务层传递到持久层的参数
 * @author: zyb
 * @since: 2020/4/8 17:21
 */
@Data
public class CommentDTO {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 帖子id
     */
    private Integer postId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 评论内容
     */
    private String comment;
    /**
     * 创建时间
     */
    private Date createTime;
}
