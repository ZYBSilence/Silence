package cn.graduation.bbs.vo.role;

import lombok.Data;

/**
 * @desc: 角色管理服务端返回到前端的参数
 * @author: zyb
 * @since: 2020/4/10 17:36
 */
@Data
public class RoleVO {
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
    /**
     * 用户数量
     */
    private Integer userCount;
}
