package cn.graduation.bbs.service.impl;

import cn.graduation.bbs.common.GradException;
import cn.graduation.bbs.common.ListPage;
import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.dao.PostDao;
import cn.graduation.bbs.dto.post.PostDTO;
import cn.graduation.bbs.entity.PostEntity;
import cn.graduation.bbs.enums.StatusCodeEnum;
import cn.graduation.bbs.service.PostService;
import cn.graduation.bbs.utils.EmptyUtils;
import cn.graduation.bbs.vo.post.PostFilter;
import cn.graduation.bbs.vo.post.PostVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @desc: 帖子管理的业务层实现类
 * @author: zyb
 * @since: 2020/4/2 11:49
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;

    /**
     * 查询帖子列表
     *
     * @param postFilter
     * @return
     */
    @Override
    public WebResponse queryPostList(PostFilter postFilter) {
        if (EmptyUtils.isEmpty(postFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PageHelper.startPage(postFilter.getPageNum(), postFilter.getPageSize());
        List<PostEntity> postEntities = postDao.queryPostList(genPostFilter(postFilter));
        //查询数据为空
        if (postEntities == null || postEntities.size() == 0) {
            web.setCode(StatusCodeEnum.DATA_IS_NULL.getCode());
            web.setMessage("未查询到符合条件的帖子");
            return web;
        }
        ListPage<PostEntity> postListPage = new ListPage<>(postEntities);
        List<PostVO> list = new ArrayList<>();
        postEntities.forEach(v -> {
            PostVO vo = genPostEntity(v);
            list.add(vo);
        });
        web.setData(new ListPage<>(list, postListPage.getPage()));
        return web;
    }

    /**
     * 根据id查看帖子详情
     *
     * @param postFilter
     * @return
     */
    @Override
    public WebResponse queryPostById(PostFilter postFilter) {
        if (EmptyUtils.isEmpty(postFilter) || EmptyUtils.isEmpty(postFilter.getId())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PostEntity postEntity = postDao.queryPostById(postFilter.getId());
        web.setData(postEntity);
        return web;
    }

    /**
     * 修改帖子推荐状态
     *
     * @param postFilter
     * @return
     */
    @Override
    public WebResponse modifyRecommend(PostFilter postFilter) {
        if (EmptyUtils.isEmpty(postFilter) || EmptyUtils.isEmpty(postFilter.getId()) || EmptyUtils.isEmpty(postFilter.getRecommend())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PostDTO dto = new PostDTO();
        dto.setAddRecommendTime(new Date());
        dto.setId(postFilter.getId());
        dto.setRecommend(postFilter.getRecommend());
        postDao.modifyRecommend(dto);
        return web;
    }

    /**
     * 修改帖子封禁状态
     *
     * @param postFilter
     * @return
     */
    @Override
    public WebResponse modifyStatus(PostFilter postFilter) {
        if (EmptyUtils.isEmpty(postFilter) || EmptyUtils.isEmpty(postFilter.getId()) || EmptyUtils.isEmpty(postFilter.getStatus())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PostDTO dto = new PostDTO();
        dto.setAddBannedTime(new Date());
        dto.setId(postFilter.getId());
        dto.setStatus(postFilter.getStatus());
        postDao.modifyStatus(dto);
        return web;
    }

    /**
     * 批量封禁帖子
     *
     * @param postFilter
     * @return
     */
    @Override
    public WebResponse bannedPost(PostFilter postFilter) {
        if (EmptyUtils.isEmpty(postFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        if (postFilter.getIds().size() <= 0) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("请选择要封禁的帖子");
            return web;
        }
        postDao.batchModifyStatus(postFilter.getIds());
        return web;
    }

    /**
     * 根据id集合批量删除帖子
     *
     * @param postFilter
     * @return
     */
    @Override
    public WebResponse delete(PostFilter postFilter) {
        if (EmptyUtils.isEmpty(postFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        if (postFilter.getIds().size() <= 0) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("请选择要删除的帖子");
            return web;
        }
        postDao.delete(postFilter.getIds());
        return web;
    }

    /**
     * 批量解封帖子
     *
     * @param postFilter
     * @return
     */
    @Override
    public WebResponse unBanPost(PostFilter postFilter) {
        if (EmptyUtils.isEmpty(postFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        if (postFilter.getIds().size() <= 0) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("请选择要解封的帖子");
            return web;
        }
        postDao.unBanPost(postFilter.getIds());
        return web;
    }


    /**
     * 处理返回给持久层的参数
     *
     * @param postFilter
     * @return
     */
    private PostDTO genPostFilter(PostFilter postFilter) {
        PostDTO dto = new PostDTO();
        Optional.ofNullable(postFilter.getUserId()).ifPresent(dto::setUserId);
        Optional.ofNullable(postFilter.getTitle()).ifPresent(dto::setTitle);
        Optional.ofNullable(postFilter.getStatus()).ifPresent(dto::setStatus);
        Optional.ofNullable(postFilter.getRecommend()).ifPresent(dto::setRecommend);
        Optional.ofNullable(postFilter.getStartTime()).ifPresent(dto::setStartTime);
        Optional.ofNullable(postFilter.getEndTime()).ifPresent(dto::setEndTime);
        return dto;
    }

    /**
     * 处理返回给前端的参数
     *
     * @param v
     * @return
     */
    private PostVO genPostEntity(PostEntity v) {
        PostVO vo = new PostVO();
        Optional.ofNullable(v.getId()).ifPresent(vo::setId);
        Optional.ofNullable(v.getUserId()).ifPresent(vo::setUserId);
        Optional.ofNullable(v.getNickName()).ifPresent(vo::setNickname);
        Optional.ofNullable(v.getTitle()).ifPresent(vo::setTitle);
        Optional.ofNullable(v.getPostType()).ifPresent(vo::setPostType);
        Optional.ofNullable(v.getRecommend()).ifPresent(vo::setRecommend);
        Optional.ofNullable(v.getStatus()).ifPresent(vo::setStatus);
        Optional.ofNullable(v.getCreateTime()).ifPresent(vo::setCreateTime);
        Optional.ofNullable(v.getUpdateTime()).ifPresent(vo::setUpdateTime);
        Optional.ofNullable(v.getAddRecommendTime()).ifPresent(vo::setAddRecommendTime);
        Optional.ofNullable(v.getAddBannedTime()).ifPresent(vo::setAddBannedTime);
        return vo;
    }
}
