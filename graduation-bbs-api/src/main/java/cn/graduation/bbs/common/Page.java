package cn.graduation.bbs.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @desc: 分页查询page类
 * @author: zyb
 * @since: 2020/1/2 11:14
 */
@NoArgsConstructor
@Data
public class Page implements Serializable {
    /**
     * 总数据数
     */
    private Long total;

    /**
     * 当前页码
     */
    private Integer pageNum = 1;

    /**
     * 每页行数（数据量）
     */
    private Integer pageSize = 10;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 开始行
     */
    private int startRow;

    /**
     * 结束行
     */
    private int endRow;
}
