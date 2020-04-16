package cn.graduation.bbs.service.impl;

import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.dao.RoleDao;
import cn.graduation.bbs.dao.UserDao;
import cn.graduation.bbs.entity.RoleEntity;
import cn.graduation.bbs.service.RoleService;
import cn.graduation.bbs.vo.role.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @desc: 角色管理的业务层实现类
 * @author: zyb
 * @since: 2020/3/16 14:34
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 查询角色列表
     *
     * @return
     */
    @Override
    public WebResponse queryRoleList() {
        WebResponse web = new WebResponse();
        List<RoleEntity> roleEntities = roleDao.queryRoleList();
        List<RoleVO> list = new ArrayList<>();
        roleEntities.forEach(v -> {
            RoleVO vo = genRoleEntity(v);
            list.add(vo);
        });
        web.setData(list);
        return web;
    }

    /**
     * 处理返回给前端的参数
     *
     * @return
     */
    private RoleVO genRoleEntity(RoleEntity entity) {
        RoleVO vo = new RoleVO();
        Optional.ofNullable(entity.getId()).ifPresent(vo::setId);
        Optional.ofNullable(entity.getRoleName()).ifPresent(vo::setRoleName);
        Optional.ofNullable(entity.getRoleDesc()).ifPresent(vo::setRoleDesc);
        Optional.ofNullable(roleDao.queryUserCountByRoleId(entity.getId())).ifPresent(vo::setUserCount);
        return vo;
    }
}
