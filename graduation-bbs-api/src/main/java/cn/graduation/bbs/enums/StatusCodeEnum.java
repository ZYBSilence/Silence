package cn.graduation.bbs.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @desc: 状态码
 * @author: zyb
 * @since: 2020/1/14 11:39
 */
public enum StatusCodeEnum {
    /**
     * 返回状态码
     */
    OK(200, "成功"),
    ERROR(201, "失败"),
    LOGIN_ERROR(202, "用户名或密码错误"),
    ACCESS_ERROR(203, "权限不足"),
    REMOTE_ERROR(204, "远程调用失败"),
    REP_ERROR(205, "重复操作"),
    PARAMS_NOT_NULL(201,"提交参数有误"),
    DATA_IS_NULL(201,"返回数据为空");

    private Integer code;
    private String message;

    StatusCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
