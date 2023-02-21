package cn.graduation.bbs.service.impl;

import cn.graduation.bbs.bean.UserPostCollectDO;
import cn.graduation.bbs.common.GradException;
import cn.graduation.bbs.common.ListPage;
import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.dao.CommentDao;
import cn.graduation.bbs.dao.PostDao;
import cn.graduation.bbs.dao.PostTypeDao;
import cn.graduation.bbs.dto.post.PostDTO;
import cn.graduation.bbs.entity.PostEntity;
import cn.graduation.bbs.entity.PostTypeEntity;
import cn.graduation.bbs.enums.CollectEnum;
import cn.graduation.bbs.enums.PostTagsEnum;
import cn.graduation.bbs.enums.StatusCodeEnum;
import cn.graduation.bbs.service.PostService;
import cn.graduation.bbs.service.PostTypeService;
import cn.graduation.bbs.utils.EmptyUtils;
import cn.graduation.bbs.utils.OperUserUtils;
import cn.graduation.bbs.vo.post.PostDetailVO;
import cn.graduation.bbs.vo.post.PostFilter;
import cn.graduation.bbs.vo.post.PostVO;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.exceptions.MySQLDataException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import sun.awt.AppContext;

import javax.annotation.Resource;
import java.sql.SQLException;
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

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PostTypeDao postTypeDao;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private PostService postService;

    @Resource
    private TransactionTemplate transactionTemplate;

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
        PostDetailVO vo = new PostDetailVO();
        Optional.ofNullable(postEntity.getId()).ifPresent(vo::setId);
        Optional.ofNullable(postEntity.getTitle()).ifPresent(vo::setTitle);
        Optional.ofNullable(postEntity.getContent()).ifPresent(vo::setContent);
        Optional.ofNullable(postEntity.getUserId()).ifPresent(vo::setUserId);
        Optional.ofNullable(postEntity.getPostTypeId()).ifPresent(vo::setPostTypeId);
        Optional.ofNullable(postEntity.getRecommend()).ifPresent(vo::setRecommend);
        Optional.ofNullable(postEntity.getStatus()).ifPresent(vo::setStatus);
        Optional.ofNullable(postEntity.getCreateTime()).ifPresent(vo::setCreateTime);
        Optional.ofNullable(postEntity.getUpdateTime()).ifPresent(vo::setUpdateTime);
        Optional.ofNullable(postEntity.getAddBannedTime()).ifPresent(vo::setAddBannedTime);
        Optional.ofNullable(postEntity.getAddRecommendTime()).ifPresent(vo::setAddRecommendTime);
        Optional.ofNullable(postEntity.getPostType()).ifPresent(vo::setPostType);
        Optional.ofNullable(postEntity.getUsername()).ifPresent(vo::setUsername);
        Optional.ofNullable(postEntity.getNickName()).ifPresent(vo::setNickName);
        Optional.ofNullable(postEntity.getPhoto()).ifPresent(vo::setPhoto);
        Optional.ofNullable(commentDao.queryCommentCountByPostId(postEntity.getId())).ifPresent(vo::setCommentCount);
        //如果当前用户已登录，则查询这个帖子是否被收藏，是否点赞此帖子
        if (!EmptyUtils.isEmpty(OperUserUtils.getUserId())) {
            Integer collect = postDao.queryUserCollect(OperUserUtils.getUserId(), postFilter.getId());
            if (EmptyUtils.isEmpty(collect) || collect == 0) {
                vo.setCollect(CollectEnum.COLLECT_NO.getCode());
            } else {
                vo.setCollect(CollectEnum.COLLECT_YES.getCode());
            }
            Integer tags = postDao.queryUserPostTags(OperUserUtils.getUserId(), postFilter.getId());
            if (EmptyUtils.isEmpty(tags) || tags == 0) {
                vo.setPostTags(PostTagsEnum.TAGS_NO.getCode());
            } else {
                vo.setPostTags(PostTagsEnum.TAGS_YES.getCode());
            }
        }
        Optional.ofNullable(postDao.queryUserCollect(null, postEntity.getId())).ifPresent(vo::setCollectCount);
        Optional.ofNullable(postDao.queryUserPostTags(null, postEntity.getId())).ifPresent(vo::setPostTagsCount);
        web.setData(vo);
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
     * 新增帖子
     *
     * @param postFilter
     * @return
     */
    @Override
    public WebResponse addPost(PostFilter postFilter) {
        if (EmptyUtils.isEmpty(postFilter) || EmptyUtils.isEmpty(postFilter.getPostTypeId()) || EmptyUtils.isEmpty(postFilter.getTitle())
                || EmptyUtils.isEmpty(postFilter.getContent())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PostDTO dto = new PostDTO();
        Optional.ofNullable(OperUserUtils.getUserId()).ifPresent(dto::setUserId);
        Optional.ofNullable(postFilter.getPostTypeId()).ifPresent(dto::setPostTypeId);
        Optional.ofNullable(postFilter.getTitle()).ifPresent(dto::setTitle);
        Optional.ofNullable(postFilter.getContent()).ifPresent(dto::setContent);
        dto.setCreateTime(new Date());
        postDao.addPost(dto);
        return web;
    }

    /**
     * 修改帖子
     *
     * @param postFilter
     * @return
     */
    @Override
    public WebResponse updatePost(PostFilter postFilter) {
        if (EmptyUtils.isEmpty(postFilter) || EmptyUtils.isEmpty(postFilter.getId()) || EmptyUtils.isEmpty(postFilter.getPostTypeId())
                || EmptyUtils.isEmpty(postFilter.getTitle()) || EmptyUtils.isEmpty(postFilter.getContent())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PostDTO dto = new PostDTO();
        Optional.ofNullable(OperUserUtils.getUserId()).ifPresent(dto::setUserId);
        Optional.ofNullable(postFilter.getId()).ifPresent(dto::setId);
        Optional.ofNullable(postFilter.getPostTypeId()).ifPresent(dto::setPostTypeId);
        Optional.ofNullable(postFilter.getTitle()).ifPresent(dto::setTitle);
        Optional.ofNullable(postFilter.getContent()).ifPresent(dto::setContent);
        dto.setUpdateTime(new Date());
        postDao.updatePost(dto);
        return web;
    }

    /**
     * 分页查询用户收藏帖子列表
     *
     * @param postFilter
     * @return
     */
    @Override
    public WebResponse queryUserCollectList(PostFilter postFilter) {
        if (EmptyUtils.isEmpty(postFilter) || EmptyUtils.isEmpty(postFilter.getUserId())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PageHelper.startPage(postFilter.getPageNum(), postFilter.getPageSize());
        List<UserPostCollectDO> userCollectList = postDao.queryUserCollectList(postFilter.getUserId());
        //查询数据为空
        if (userCollectList == null || userCollectList.size() == 0) {
            web.setCode(StatusCodeEnum.DATA_IS_NULL.getCode());
            web.setMessage("未查询到符合条件的帖子");
            return web;
        }
        web.setData(new ListPage<>(userCollectList));
        return web;
    }

    /**
     * 修改帖子收藏状态
     *
     * @param postFilter
     * @return
     */
    @Override
    public WebResponse modifyCollect(PostFilter postFilter) {
        if (EmptyUtils.isEmpty(postFilter) || EmptyUtils.isEmpty(postFilter.getId()) || EmptyUtils.isEmpty(postFilter.getCollect())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        Integer userId = OperUserUtils.getUserId();
        //从前端接collect（用户帖子状态是已收藏则取消收藏，状态是未收藏则添加收藏）
        if (postFilter.getCollect().equals(CollectEnum.COLLECT_YES.getCode())) {
            postDao.deleteCollect(userId, postFilter.getId());
        } else if (postFilter.getCollect().equals(CollectEnum.COLLECT_NO.getCode())) {
            postDao.addCollect(userId, postFilter.getId(), new Date());
        }
        return web;
    }

    /**
     * 修改帖子点赞状态
     *
     * @param postFilter
     * @return
     */
    @Override
    public WebResponse modifyPostTags(PostFilter postFilter) {
        if (EmptyUtils.isEmpty(postFilter) || EmptyUtils.isEmpty(postFilter.getId()) || EmptyUtils.isEmpty(postFilter.getTags())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        Integer userId = OperUserUtils.getUserId();
        //从前端接tags（用户帖子状态是已点赞则取消点赞，状态是未点赞则点赞）
        if (postFilter.getTags().equals(PostTagsEnum.TAGS_YES.getCode())) {
            postDao.deletePostTags(userId, postFilter.getId());
        } else if (postFilter.getTags().equals(PostTagsEnum.TAGS_NO.getCode())) {
            postDao.addPostTags(userId, postFilter.getId());
        }
        return web;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void testTranslation(){
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("aaa");
        postDTO.setContent("zzz");
        postDTO.setUserId(123);
        postDTO.setPostTypeId(1);
        postDTO.setRecommend(0);
        postDTO.setStatus(0);
        postDTO.setCreateTime(new Date());
        postDao.addPost(postDTO);
//        ((PostServiceImpl) AopContext.currentProxy()).testTranslation2();
//        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        transactionTemplate.execute(transactionStatus -> {
//            testTranslation2();
//            return Boolean.TRUE;
//        });

//        postService.testTranslation2();
        throw new RuntimeException("aaa");

    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    public void testTranslation2(){
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("bbb");
        postDTO.setContent("zzz");
        postDTO.setUserId(123);
        postDTO.setPostTypeId(1);
        postDTO.setRecommend(0);
        postDTO.setStatus(0);
        postDTO.setCreateTime(new Date());
        postDao.addPost(postDTO);
        throw new RuntimeException("aaa");
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
        Optional.ofNullable(postFilter.getPostTypeId()).ifPresent(dto::setPostTypeId);
        Optional.ofNullable(postFilter.getPostType()).ifPresent(dto::setPostType);
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
        Optional.ofNullable(v.getPhoto()).ifPresent(vo::setPhoto);
        Optional.ofNullable(v.getTitle()).ifPresent(vo::setTitle);
        Optional.ofNullable(v.getPostType()).ifPresent(vo::setPostType);
        Optional.ofNullable(v.getRecommend()).ifPresent(vo::setRecommend);
        Optional.ofNullable(v.getStatus()).ifPresent(vo::setStatus);
        Optional.ofNullable(v.getCreateTime()).ifPresent(vo::setCreateTime);
        Optional.ofNullable(v.getUpdateTime()).ifPresent(vo::setUpdateTime);
        Optional.ofNullable(v.getAddRecommendTime()).ifPresent(vo::setAddRecommendTime);
        Optional.ofNullable(v.getAddBannedTime()).ifPresent(vo::setAddBannedTime);
        Optional.ofNullable(commentDao.queryCommentCountByPostId(v.getId())).ifPresent(vo::setCommentCount);
        return vo;
    }
}
