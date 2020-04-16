package cn.graduation.bbs.service;

import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.vo.comment.CommentFilter;

/**
 * @desc: 评论管理的业务层接口
 * @author: zyb
 * @since: 2020/4/8 17:11
 */
public interface CommentService {
    /**
     * 条件查询评论列表
     *
     * @param commentFilter
     * @return
     */
    WebResponse queryCommentList(CommentFilter commentFilter);

    /**
     * 根据id集合批量删除评论
     *
     * @param commentFilter
     * @return
     */
    WebResponse delete(CommentFilter commentFilter);

    /**
     * 根绝id查看评论详情
     *
     * @param commentFilter
     * @return
     */
    WebResponse queryCommentById(CommentFilter commentFilter);
}
