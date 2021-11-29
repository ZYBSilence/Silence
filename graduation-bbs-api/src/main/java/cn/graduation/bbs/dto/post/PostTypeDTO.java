package cn.graduation.bbs.dto.post;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc:
 * @author: zyb
 * @since: 2020/4/8 9:55
 */
@Data
@NoArgsConstructor
public class PostTypeDTO {
    /**
     * 帖子类型id
     */
    private Integer id;
    /**
     * 帖子类型
     */
    private String postType;

    public PostTypeDTO(String postType) {
        this.postType = postType;
    }
}
