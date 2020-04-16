package cn.graduation.bbs.dao;

import cn.graduation.bbs.entity.RoleEntity;

import java.util.List;

/**
 * @desc: 角色管理的dao层
 * @author: zyb
 * @since: 2020/3/13 15:57
 */
public interface RoleDao {

    /**
     * 查询角色列表
     *
     * @return
     */
    List<RoleEntity> queryRoleList();

    /**
     * 保存用户id和角色id关联信息
     *
     * @param userId
     * @param roleId
     * @return
     */
    boolean saveUr(Integer userId, Integer roleId);

    /**
     * 修改用户id和角色id关联信息
     *
     * @param userId
     * @param roleId
     * @return
     */
    boolean updateUr(Integer userId, Integer roleId);

    /**
     * 根绝角色id查询用户数量
     *
     * @param roleId
     * @return
     */
    Integer queryUserCountByRoleId(Integer roleId);
}
