package cn.graduation.bbs.dao;

import cn.graduation.bbs.bean.PostCommentCountDO;
import cn.graduation.bbs.bean.UserCommentCountDO;
import cn.graduation.bbs.dto.comment.CommentDTO;
import cn.graduation.bbs.entity.CommentEntity;
import cn.graduation.bbs.vo.post.PostTypeFilter;

import java.util.List;

/**
 * @desc: 评论管理dao层
 * @author: zyb
 * @since: 2020/4/8 17:15
 */
public interface CommentDao {

    /**
     * 条件查询评论列表
     *
     * @param dto
     * @return
     */
    List<CommentEntity> queryCommentList(CommentDTO dto);

    /**
     * 根据id集合批量删除评论
     *
     * @param ids
     * @return
     */
    boolean delete(List<Integer> ids);

    /**
     * 根绝id查看评论详情
     *
     * @param id
     * @return
     */
    CommentEntity queryCommentById(Integer id);

    /**
     * 根据帖子id查询评论数量
     *
     * @param postId
     * @return
     */
    Integer queryCommentCountByPostId(Integer postId);

    /**
     * 查询用户评论数量列表（可以根据帖子类型id查找）
     *
     * @param filter
     * @return
     */
    List<UserCommentCountDO> queryCommentCountList(PostTypeFilter filter);

    /**
     * 查询帖子评论数量列表（可以根据帖子类型id查找）
     *
     * @param filter
     * @return
     */
    List<PostCommentCountDO> queryPostCommentCountList(PostTypeFilter filter);

    /**
     * 提交评论
     *
     * @param dto
     * @return
     */
    boolean addComment(CommentDTO dto);

    /**
     * 分页查询用户帖子的评论（不包含自己的评论）
     *
     * @param userId
     * @return
     */
    List<CommentEntity> queryUserPostCommentList(Integer userId);

    /**
     * 根据用户id、帖子id查询用户-评论点赞表
     *
     * @param userId
     * @param commentId
     * @return
     */
    Integer queryUserCommentTags(Integer userId, Integer commentId);

    /**
     * 根据用户id和评论id删除用户点赞标记
     *
     * @param userId
     * @param commentId
     * @return
     */
    boolean deleteCommentTags(Integer userId, Integer commentId);

    /**
     * 根据用户id和评论id添加用户点赞标记
     *
     * @param userId
     * @param commentId
     * @return
     */
    boolean addCommentTags(Integer userId, Integer commentId);
}
