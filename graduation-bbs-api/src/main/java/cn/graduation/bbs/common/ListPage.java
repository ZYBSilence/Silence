package cn.graduation.bbs.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: 分页查询返回数据类
 * @author: zyb
 * @since: 2020/1/2 11:12
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListPage<T> implements Serializable {
    /**
     * 当前分页所查找的数据
     */
    private List<T> list;

    /**
     * 当前页码相关类
     */
    private Page page;

    public ListPage(List<T> list) {
        page = new Page();
        if (list instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page<T> jgpage = (com.github.pagehelper.Page<T>) list;
            this.page.setPageNum(jgpage.getPageNum());
            this.page.setPageSize(jgpage.getPageSize());
            this.page.setTotal(jgpage.getTotal());
            this.page.setPages(jgpage.getPages());
            this.page.setStartRow(jgpage.getStartRow());
            this.page.setEndRow(jgpage.getEndRow());
        }
        this.list = list;
    }
}
