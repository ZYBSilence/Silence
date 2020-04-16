package cn.graduation.bbs.dao;

import cn.graduation.bbs.dto.user.UserDTO;
import cn.graduation.bbs.entity.UserEntity;
import cn.graduation.bbs.vo.user.UserDelFilter;

import java.util.List;

/**
 * @desc: 用户管理的dao层
 * @author: zyb
 * @since: 2019/12/31 17:05
 */
public interface UserDao {

    /**
     * 根绝条件分页查询用户信息
     *
     * @param userDTO
     * @return
     */
    List<UserEntity> queryUserList(UserDTO userDTO);


    /**
     * 新增用户/注册用户
     *
     * @param userEntity
     * @return
     */
    boolean save(UserEntity userEntity);

    /**
     * 更新用户
     *
     * @param userEntity
     * @return
     */
    boolean update(UserEntity userEntity);

    /**
     * 根据id集合批量删除用户
     *
     * @param ids
     * @return
     */
    boolean delete(List<Integer> ids);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    UserEntity queryById(Integer id);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    UserEntity findByUsername(String username);

    /**
     * 根据用户id集合查询用户集合
     *
     * @param ids
     * @return
     */
    List<UserEntity> findByUserIdList(List<Integer> ids);

    /**
     * 修改用户的封禁状态
     *
     * @param dto
     * @return
     */
    boolean modifyDelFlag(UserDTO dto);

    /**
     * 批量封禁用户
     *
     * @param ids
     * @return
     */
    boolean batchBannedUser(List<Integer> ids);

    /**
     * 批量解封用户
     *
     * @param ids
     * @return
     */
    boolean unBanUser(List<Integer> ids);
}
