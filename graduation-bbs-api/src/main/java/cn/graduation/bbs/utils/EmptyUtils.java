package cn.graduation.bbs.utils;

import java.util.Collection;
import java.util.List;

/**
 * @desc: 判空工具类
 * @author: zyb
 * @since: 2019/12/31 18:21
 */
public class EmptyUtils {

    /**
     * 私有EmptyUtils构造类，让外界不能创建对象，只能静态调用方法
     */
    private EmptyUtils() {
    }

    /**
     * 功能描述: Object 等于空
     */
    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    /**
     * 功能描述: Object 不等于空
     */
    public static boolean isNotEmpty(Object obj) {
        return obj != null;
    }

    /**
     * 功能描述: str 等于空
     */
    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str) || (str.trim().length() == 0));
    }


    /**
     * 功能描述: str 不等于空
     */
    public static boolean isNotEmpty(String str) {
        return (str != null && !"".equals(str));
    }

    /**
     * 功能描述: int数字类型为空
     */
    public static boolean isEmpty(Integer num) {
        return num == null;
    }

    /**
     * 功能描述: int数字类型不为空
     */
    public static boolean isNotEmpty(Integer num) {
        return num != null;
    }


    /**
     * 功能描述: 判断list集合为空：list == null 或 list.size ==0  返回 true
     */
    @SuppressWarnings("unchecked")
    public static boolean isEmpty(List list) {
        return (list == null || list.size() == 0);
    }


    /**
     * 功能描述: 判断list集合不为空：list != null 或 list.size >0  返回 true
     */
    @SuppressWarnings("unchecked")
    public static boolean isNotEmpty(List list) {
        return (list != null && list.size() > 0);
    }


    /**
     * 功能描述: 对象数组为空
     */
    public static boolean isEmpty(Object[] obj) {
        return ((obj == null) || (obj.length == 0));
    }


    /**
     * 功能描述: 对象数组不为空
     */
    public static boolean isNotEmpty(Object[] obj) {
        return ((obj != null) && (obj.length > 0));
    }


    /**
     * 功能描述: collection集合为空
     */
    public static boolean isEmpty(Collection<?> conn) {
        return ((conn == null) || (conn.size() <= 0));
    }

    /**
     * 功能描述: collection集合不为空
     */
    public static boolean isNotEmpty(Collection<?> conn) {
        return ((conn != null) && (conn.size() > 0));
    }

    /**
     * 功能描述: byte数组为空
     */
    public static boolean isEmpty(byte[] bys) {
        return ((bys == null) || (bys.length == 0));
    }

    /**
     * 功能描述: byte数组不为空
     */
    public static boolean isNotEmpty(byte[] bys) {
        return ((bys != null) && (bys.length > 0));
    }

    /**
     * 功能描述: long类型为空
     */
    public static boolean isEmpty(Long l) {
        return ((l == null) || (l.longValue() == 0L));
    }


    /**
     * 功能描述: long类型不为空
     */
    public static boolean isNotEmpty(Long l) {
        return ((l != null) && (l.longValue() > 0L));
    }
}
