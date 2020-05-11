package cn.graduation.bbs.enums;

/**
 * @desc: 帖子/评论点赞状态
 * @author: zyb
 * @since: 2020/5/11 12:05
 */
public enum PostTagsEnum {
    /**
     * 帖子点赞状态
     */
    TAGS_YES(1, "已点赞"),
    TAGS_NO(0, "未点赞");

    private Integer code;
    private String desc;

    PostTagsEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
