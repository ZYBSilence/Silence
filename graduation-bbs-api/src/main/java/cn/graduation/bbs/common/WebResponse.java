package cn.graduation.bbs.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc: HTTP 统一响应结果
 * @author: zyb
 * @since: 2019/12/31 10:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    private Integer code = 200;

    /**
     * 提示信息
     */
    private String message = "success";

    /**
     * 数据
     */
    private T data;

    public WebResponse(T data) {
        this.data = data;
    }

    public WebResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
