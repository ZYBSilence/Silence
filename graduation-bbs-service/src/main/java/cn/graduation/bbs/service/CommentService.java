package cn.graduation.bbs.service;

import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.dto.post.PostTypeDTO;
import cn.graduation.bbs.vo.comment.CommentFilter;
import cn.graduation.bbs.vo.post.PostTypeFilter;

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

    /**
     * 查找用户评论数量列表
     *
     * @param filter
     * @return
     */
    WebResponse queryUserCommentCountList(PostTypeFilter filter);

    /**
     * 查找帖子评论数量列表
     *
     * @param filter
     * @return
     */
    WebResponse queryPostCommentCountList(PostTypeFilter filter);

    /**
     * 提交评论
     *
     * @param commentFilter
     * @return
     */
    WebResponse addComment(CommentFilter commentFilter);

    /**
     * 分页查询用户帖子的评论（不包含自己的评论）
     *
     * @param commentFilter
     * @return
     */
    WebResponse queryUserPostCommentList(CommentFilter commentFilter);

    /**
     * 修改评论点赞状态
     *
     * @param commentFilter
     * @return
     */
    WebResponse modifyCommentTags(CommentFilter commentFilter);

    void testSave(PostTypeDTO dto);
}
