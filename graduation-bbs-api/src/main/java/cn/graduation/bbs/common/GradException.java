package cn.graduation.bbs.common;

/**
 * @desc: 异常处理
 * @author: zyb
 * @since: 2020/2/21 9:05
 */
public class GradException extends RuntimeException {
    public GradException(String message) {
        super(message);
    }
}
