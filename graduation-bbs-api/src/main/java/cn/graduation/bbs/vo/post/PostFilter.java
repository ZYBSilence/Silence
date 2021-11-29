package cn.graduation.bbs.vo.post;

import cn.graduation.bbs.common.Page;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @desc: 帖子管理前端传给服务端的参数
 * @author: zyb
 * @since: 2020/4/2 11:46
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "帖子管理前端传参JavaBean")
@Data
public class PostFilter extends Page {
    private static final long serialVersionUID = -3635421740548207474L;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 帖子标题
     */
    @ApiModelProperty(value = "帖子标题")
    private String title;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 帖子id
     */
    private Integer id;
    /**
     * 是否为推荐（0-否，1-是）
     */
    private Integer recommend;
    /**
     * 状态（0-正常，1-封禁）
     */
    private Integer status;
    /**
     * id集合
     */
    private List<Integer> ids;
    /**
     * 帖子内容
     */
    private String content;
    /**
     * 帖子类型id
     */
    private Integer postTypeId;
    /**
     * 帖子类型
     */
    private String postType;
    /**
     * 帖子收藏状态
     */
    private Integer collect;
    /**
     * 帖子点赞状态
     */
    private Integer tags;
}
