package cn.graduation.bbs.utils;

import java.util.UUID;

/**
 * @desc: 产生UUID随机字符串工具类
 * @author: zyb
 * @since: 2020/4/14 15:37
 */
public final class UuidUtil {
    private UuidUtil() {
    }

    private static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        System.out.println(UuidUtil.getUuid());
        System.out.println(UuidUtil.getUuid());
    }
}
