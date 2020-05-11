package cn.graduation.bbs.service;

import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.vo.post.PostFilter;

/**
 * @desc: 帖子管理的业务层接口
 * @author: zyb
 * @since: 2020/4/2 11:49
 */
public interface PostService {
    /**
     * 查询帖子列表
     *
     * @param postFilter
     * @return
     */
    WebResponse queryPostList(PostFilter postFilter);

    /**
     * 根绝id查看帖子详情
     *
     * @param postFilter
     * @return
     */
    WebResponse queryPostById(PostFilter postFilter);

    /**
     * 修改帖子推荐状态
     *
     * @param postFilter
     * @return
     */
    WebResponse modifyRecommend(PostFilter postFilter);

    /**
     * 修改帖子封禁状态
     *
     * @param postFilter
     * @return
     */
    WebResponse modifyStatus(PostFilter postFilter);

    /**
     * 批量封禁帖子
     *
     * @param postFilter
     * @return
     */
    WebResponse bannedPost(PostFilter postFilter);

    /**
     * 根据id集合批量删除帖子
     *
     * @param postFilter
     * @return
     */
    WebResponse delete(PostFilter postFilter);

    /**
     * 批量解封帖子
     *
     * @param postFilter
     * @return
     */
    WebResponse unBanPost(PostFilter postFilter);

    /**
     * 新增帖子
     *
     * @param postFilter
     * @return
     */
    WebResponse addPost(PostFilter postFilter);

    /**
     * 修改帖子
     *
     * @param postFilter
     * @return
     */
    WebResponse updatePost(PostFilter postFilter);

    /**
     * 分页查询用户收藏帖子列表
     *
     * @param postFilter
     * @return
     */
    WebResponse queryUserCollectList(PostFilter postFilter);

    /**
     * 修改帖子收藏状态
     *
     * @param postFilter
     * @return
     */
    WebResponse modifyCollect(PostFilter postFilter);

    /**
     * 修改帖子点赞状态
     *
     * @param postFilter
     * @return
     */
    WebResponse modifyPostTags(PostFilter postFilter);
}
