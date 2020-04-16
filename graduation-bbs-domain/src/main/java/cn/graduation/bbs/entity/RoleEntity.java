package cn.graduation.bbs.entity;

import lombok.Data;

/**
 * @desc: 角色表
 * @author: zyb
 * @since: 2019/12/31 17:02
 */
@Data
public class RoleEntity {
    /**
     * 角色id
     */
    private Integer id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;
}
