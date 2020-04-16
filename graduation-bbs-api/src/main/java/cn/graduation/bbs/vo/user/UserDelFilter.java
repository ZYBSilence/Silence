package cn.graduation.bbs.vo.user;

import lombok.Data;

import java.util.List;

/**
 * @desc: 根据id集合批量删除用户
 * @author: zyb
 * @since: 2020/2/24 18:24
 */
@Data
public class UserDelFilter {
    private List<Integer> ids;
}
