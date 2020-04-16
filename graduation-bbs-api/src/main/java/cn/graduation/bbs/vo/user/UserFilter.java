package cn.graduation.bbs.vo.user;

import cn.graduation.bbs.common.Page;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @desc: 查询网站用户前端返回到服务端的参数
 * @author: zyb
 * @since: 2020/1/10 15:24
 */
@Data
public class UserFilter extends Page {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 封禁标记（当标为1时表示用户为封禁状态）
     */
    private Integer delFlag;
    /**
     * id集合
     */
    private List<Integer> ids;
}
