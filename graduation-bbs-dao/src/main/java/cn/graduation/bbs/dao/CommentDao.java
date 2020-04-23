package cn.graduation.bbs.dao;

import cn.graduation.bbs.bean.PostCommentCountDO;
import cn.graduation.bbs.bean.UserCommentCountDO;
import cn.graduation.bbs.bean.UserPostCountDO;
import cn.graduation.bbs.dto.comment.CommentDTO;
import cn.graduation.bbs.entity.CommentEntity;

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
     * 查询用户评论数量列表
     *
     * @return
     */
    List<UserCommentCountDO> queryCommentCountList();

    /**
     * 查询帖子评论数量列表
     *
     * @return
     */
    List<PostCommentCountDO> queryPostCommentCountList();
}
