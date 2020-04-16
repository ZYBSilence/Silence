package cn.graduation.bbs.common;

import cn.graduation.bbs.enums.StatusCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @desc: 统一处理异常类
 * @author: zyb
 * @since: 2020/1/14 11:04
 */
@RestControllerAdvice
public class BaseExceptionHander {

    @ExceptionHandler(value = Exception.class)
    public WebResponse exception(Exception e) {
        e.printStackTrace();
        return new WebResponse(StatusCodeEnum.ERROR.getCode(), StatusCodeEnum.ERROR.getMessage());
    }
}
