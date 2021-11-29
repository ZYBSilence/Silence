package cn.graduation.bbs.service.impl;

import cn.graduation.bbs.common.GradException;
import cn.graduation.bbs.common.ListPage;
import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.dao.PostDao;
import cn.graduation.bbs.dao.PostTypeDao;
import cn.graduation.bbs.dto.post.PostTypeDTO;
import cn.graduation.bbs.entity.PostTypeEntity;
import cn.graduation.bbs.enums.StatusCodeEnum;
import cn.graduation.bbs.service.CommentService;
import cn.graduation.bbs.service.PostTypeService;
import cn.graduation.bbs.utils.EmptyUtils;
import cn.graduation.bbs.vo.post.PostTypeFilter;
import cn.graduation.bbs.vo.post.PostTypeVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @desc: 帖子类型管理的业务层实现类
 * @author: zyb
 * @since: 2020/4/7 18:23
 */
@Service
public class PostTypeServiceImpl implements PostTypeService {

    @Autowired
    private PostTypeDao postTypeDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private CommentService commentService;

    /**
     * 帖子类型列表
     *
     * @param postTypeFilter
     * @return
     */
    @Override
    public WebResponse queryPostTypeList(PostTypeFilter postTypeFilter) {
        if (EmptyUtils.isEmpty(postTypeFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PageHelper.startPage(postTypeFilter.getPageNum(), postTypeFilter.getPageSize());
        List<PostTypeEntity> postTypeEntities = postTypeDao.queryPostTypeList(genPostTypeFilter(postTypeFilter));
        //查询数据为空
        if (postTypeEntities == null || postTypeEntities.size() == 0) {
            web.setCode(StatusCodeEnum.DATA_IS_NULL.getCode());
            web.setMessage("未查询到符合条件的帖子类型");
            return web;
        }
        ListPage<PostTypeEntity> postTypeListPage = new ListPage<>(postTypeEntities);
        List<PostTypeVO> list = new ArrayList<>();
        postTypeEntities.forEach(v -> {
            PostTypeVO vo = genPostTypeEntity(v);
            list.add(vo);
        });
        web.setData(new ListPage<>(list, postTypeListPage.getPage()));
        return web;
    }

    /**
     * 新增帖子类型
     *
     * @param postTypeFilter
     * @return
     */
    @Override
    public WebResponse save(PostTypeFilter postTypeFilter) {
        if (EmptyUtils.isEmpty(postTypeFilter) || EmptyUtils.isEmpty(postTypeFilter.getPostType())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        //判断帖子类型是否已经存在
        if (exist(postTypeFilter.getPostType())) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("这个帖子类型已存在");
            return web;
        }
        postTypeDao.save(genPostTypeFilter(postTypeFilter));
        return web;
    }

    /**
     * 删除帖子类型
     *
     * @param postTypeFilter
     * @return
     */
    @Override
    public WebResponse delete(PostTypeFilter postTypeFilter) {
        if (EmptyUtils.isEmpty(postTypeFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        if (postTypeFilter.getIds().size() <= 0) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("请选择要删除的帖子类型");
            return web;
        }
        postTypeDao.delete(postTypeFilter.getIds());
        return web;
    }

    /**
     * 修改帖子类型
     *
     * @param postTypeFilter
     * @return
     */
    @Override
    public WebResponse update(PostTypeFilter postTypeFilter) {
        if (EmptyUtils.isEmpty(postTypeFilter) || EmptyUtils.isEmpty(postTypeFilter.getId()) || EmptyUtils.isEmpty(postTypeFilter.getPostType())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PostTypeDTO dto = new PostTypeDTO();
        Optional.ofNullable(postTypeFilter.getId()).ifPresent(dto::setId);
        Optional.ofNullable(postTypeFilter.getPostType()).ifPresent(dto::setPostType);
        postTypeDao.update(dto);
        return web;
    }

    /**
     * 根据id查询帖子类型
     *
     * @param postTypeFilter
     * @return
     */
    @Override
    public WebResponse queryPostTypeById(PostTypeFilter postTypeFilter) {
        if (EmptyUtils.isEmpty(postTypeFilter) || EmptyUtils.isEmpty(postTypeFilter.getId())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PostTypeDTO dto = new PostTypeDTO();
        Optional.ofNullable(postTypeFilter.getId()).ifPresent(dto::setId);
        PostTypeEntity entity = postTypeDao.queryPostTypeById(dto);
        web.setData(entity);
        return web;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTranslation() {
        System.out.println("zxvc");
        commentService.testSave(new PostTypeDTO("ccc"));
        try {
            commentService.testSave(new PostTypeDTO("ddd"));
//            postTypeDao.testTranslation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testSave(PostTypeDTO dto) {
        postTypeDao.save(dto);
    }

    /**
     * 处理返回给持久层的参数
     *
     * @param filter
     * @return
     */
    private PostTypeDTO genPostTypeFilter(PostTypeFilter filter) {
        PostTypeDTO dto = new PostTypeDTO();
        Optional.ofNullable(filter.getPostType()).ifPresent(dto::setPostType);
        return dto;
    }

    /**
     * 处理返回给前端的参数
     *
     * @param entity
     * @return
     */
    private PostTypeVO genPostTypeEntity(PostTypeEntity entity) {
        PostTypeVO vo = new PostTypeVO();
        Optional.ofNullable(entity.getId()).ifPresent(vo::setId);
        Optional.ofNullable(entity.getPostType()).ifPresent(vo::setPostType);
        Optional.ofNullable(postDao.queryPostCountByPostTypeId(entity.getId())).ifPresent(vo::setPostCount);
        return vo;
    }

    /**
     * 判断帖子类型是否存在
     *
     * @param postType
     * @return
     */
    private boolean exist(String postType) {
        PostTypeEntity entity = postTypeDao.findByPostType(postType);
        return (entity != null);
    }
}
