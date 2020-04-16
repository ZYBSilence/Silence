package cn.graduation.bbs.entity;

import lombok.Data;

/**
 * @desc: 帖子类型
 * @author: zyb
 * @since: 2020/4/2 16:03
 */
@Data
public class PostTypeEntity {
    /**
     * 帖子类型id
     */
    private Integer id;
    /**
     * 帖子类型
     */
    private String postType;
}
