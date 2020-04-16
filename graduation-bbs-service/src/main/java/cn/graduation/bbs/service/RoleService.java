package cn.graduation.bbs.service;

import cn.graduation.bbs.common.WebResponse;

/**
 * @desc: 角色管理的业务层接口
 * @author: zyb
 * @since: 2020/3/16 14:33
 */
public interface RoleService {
    /**
     * 查询角色列表
     *
     * @return
     */
    WebResponse queryRoleList();
}
