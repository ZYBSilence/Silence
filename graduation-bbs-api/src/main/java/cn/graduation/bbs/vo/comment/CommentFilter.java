package cn.graduation.bbs.vo.comment;

import cn.graduation.bbs.common.Page;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @desc: 评论管理前端传给服务端的参数
 * @author: zyb
 * @since: 2020/4/8 17:08
 */
@Data
public class CommentFilter extends Page {
    /**
     * 评论id
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
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * id集合
     */
    private List<Integer> ids;
    /**
     * 评论内容
     */
    private String comment;
    /**
     * 评论点赞状态
     */
    private Integer tags;
}
