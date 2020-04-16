package cn.graduation.bbs.service;

import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.vo.post.PostTypeFilter;

/**
 * @desc:
 * @author: zyb
 * @since: 2020/4/7 18:23
 */
public interface PostTypeService {
    /**
     * 帖子类型列表
     *
     * @param postTypeFilter
     * @return
     */
    WebResponse queryPostTypeList(PostTypeFilter postTypeFilter);

    /**
     * 新增帖子类型
     *
     * @param postTypeFilter
     * @return
     */
    WebResponse save(PostTypeFilter postTypeFilter);

    /**
     * 删除帖子类型
     *
     * @param postTypeFilter
     * @return
     */
    WebResponse delete(PostTypeFilter postTypeFilter);

    /**
     * 修改帖子类型
     *
     * @param postTypeFilter
     * @return
     */
    WebResponse update(PostTypeFilter postTypeFilter);

    /**
     * 根据id查询帖子类型
     *
     * @param postTypeFilter
     * @return
     */
    WebResponse queryPostTypeById(PostTypeFilter postTypeFilter);
}
