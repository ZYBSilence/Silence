package cn.graduation.bbs.enums;

/**
 * @desc: 收藏状态
 * @author: zyb
 * @since: 2020/5/9 19:42
 */
public enum CollectEnum {
    /**
     * 收藏状态
     */
    COLLECT_YES(1, "已收藏"),
    COLLECT_NO(0, "未收藏");

    private Integer code;
    private String desc;

    CollectEnum(Integer code, String desc) {
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
