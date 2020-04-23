package cn.graduation.bbs.dao;

import cn.graduation.bbs.dto.post.PostDTO;
import cn.graduation.bbs.entity.PostEntity;

import java.util.List;

/**
 * @desc: 帖子管理dao层
 * @author: zyb
 * @since: 2020/4/1 14:19
 */
public interface PostDao {

    /**
     * 查询帖子列表
     *
     * @param postDTO
     * @return
     */
    List<PostEntity> queryPostList(PostDTO postDTO);

    /**
     * 根据id查看帖子详情
     *
     * @param id
     * @return
     */
    PostEntity queryPostById(Integer id);

    /**
     * 修改帖子推荐状态
     *
     * @param dto
     * @return
     */
    boolean modifyRecommend(PostDTO dto);

    /**
     * 修改帖子封禁状态
     *
     * @param dto
     * @return
     */
    boolean modifyStatus(PostDTO dto);

    /**
     * 批量封禁帖子
     *
     * @param ids
     * @return
     */
    boolean batchModifyStatus(List<Integer> ids);

    /**
     * 批量删除帖子
     *
     * @param ids
     * @return
     */
    boolean delete(List<Integer> ids);

    /**
     * 批量解封帖子
     *
     * @param ids
     * @return
     */
    boolean unBanPost(List<Integer> ids);

    /**
     * 根据帖子类型id查询帖子数量
     *
     * @param postTypeId
     * @return
     */
    Integer queryPostCountByPostTypeId(Integer postTypeId);

    /**
     * 新增帖子
     *
     * @param dto
     * @return
     */
    boolean addPost(PostDTO dto);

    /**
     * 根据帖子id集合查询帖子集合
     *
     * @param ids
     * @return
     */
    List<PostEntity> findByPostIdList(List<Integer> ids);
}
