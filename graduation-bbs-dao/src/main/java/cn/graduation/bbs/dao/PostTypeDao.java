package cn.graduation.bbs.dao;

import cn.graduation.bbs.dto.post.PostTypeDTO;
import cn.graduation.bbs.entity.PostTypeEntity;

import java.util.List;

/**
 * @desc: 帖子类型管理dao层
 * @author: zyb
 * @since: 2020/4/2 16:05
 */
public interface PostTypeDao {
    /**
     * 查询帖子类型列表
     *
     * @param dto
     * @return
     */
    List<PostTypeEntity> queryPostTypeList(PostTypeDTO dto);

    /**
     * 根据帖子类型id集合查询帖子类型列表
     *
     * @param ids
     * @return
     */
    List<PostTypeEntity> findPostTypeByIds(List<Integer> ids);

    /**
     * 新增帖子类型
     *
     * @param dto
     * @return
     */
    boolean save(PostTypeDTO dto);


    /**
     * 根据id集合删除帖子类型
     *
     * @param ids
     * @return
     */
    boolean delete(List<Integer> ids);

    /**
     * 根据帖子类型名称查询帖子类型
     *
     * @param postType
     * @return
     */
    PostTypeEntity findByPostType(String postType);

    /**
     * 修改帖子类型
     *
     * @param dto
     * @return
     */
    boolean update(PostTypeDTO dto);

    /**
     * 根据id查询帖子类型
     *
     * @param dto
     * @return
     */
    PostTypeEntity queryPostTypeById(PostTypeDTO dto);
}
