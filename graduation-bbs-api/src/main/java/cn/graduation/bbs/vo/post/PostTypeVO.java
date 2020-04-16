package cn.graduation.bbs.vo.post;

import lombok.Data;

/**
 * @desc: 查询帖子类型列表服务端返回到前端的参数
 * @author: zyb
 * @since: 2020/4/7 18:26
 */
@Data
public class PostTypeVO {
    /**
     * 帖子类型id
     */
    private Integer id;
    /**
     * 帖子类型
     */
    private String postType;
    /**
     * 帖子数量
     */
    private Integer postCount;
}
