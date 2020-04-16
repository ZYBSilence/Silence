package cn.graduation.bbs.service;

import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.entity.UserEntity;
import cn.graduation.bbs.vo.user.UserDelFilter;
import cn.graduation.bbs.vo.user.UserFilter;

/**
 * @desc: 用户管理的业务层接口
 * @author: zyb
 * @since: 2019/12/31 17:42
 */
public interface UserService {

    /**
     * 根据条件分页查询用户信息
     *
     * @param userFilter
     * @return
     */
    WebResponse queryUserList(UserFilter userFilter);

    /**
     * 新增用户/注册用户
     *
     * @param userEntity
     * @return
     */
    WebResponse save(UserEntity userEntity);

    /**
     * 更新用户信息
     *
     * @param userEntity
     * @return
     */
    WebResponse update(UserEntity userEntity);

    /**
     * 根据id集合批量删除用户
     *
     * @param userDelFilter
     * @return
     */
    WebResponse delete(UserDelFilter userDelFilter);

    /**
     * 根据id查询用户信息
     *
     * @param userEntity
     * @return
     */
    WebResponse queryById(UserEntity userEntity);

    /**
     * 根据用户名查询用户信息
     *
     * @param userEntity
     * @return
     */
    WebResponse queryByUsername(UserEntity userEntity);

    /**
     * 修改用户的封禁状态
     *
     * @param userFilter
     * @return
     */
    WebResponse modifyDelFlag(UserFilter userFilter);

    /**
     * 批量封禁用户
     *
     * @param userFilter
     * @return
     */
    WebResponse bannedUser(UserFilter userFilter);

    /**
     * 批量解封用户
     *
     * @param userFilter
     * @return
     */
    WebResponse unBanUser(UserFilter userFilter);
}
