package cn.graduation.bbs.vo.post;

import cn.graduation.bbs.common.Page;
import lombok.Data;

import java.util.List;

/**
 * @desc: 帖子类型管理前端传给服务端的参数
 * @author: zyb
 * @since: 2020/4/7 18:25
 */
@Data
public class PostTypeFilter extends Page {
    /**
     * 帖子类型id
     */
    private Integer id;
    /**
     * 帖子类型
     */
    private String postType;
    /**
     * id集合
     */
    private List<Integer> ids;
}
