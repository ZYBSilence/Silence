package cn.graduation.bbs.service.impl;

import cn.graduation.bbs.bean.PostCommentCountDO;
import cn.graduation.bbs.bean.UserCommentCountDO;
import cn.graduation.bbs.common.GradException;
import cn.graduation.bbs.common.ListPage;
import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.dao.CommentDao;
import cn.graduation.bbs.dao.PostDao;
import cn.graduation.bbs.dao.PostTypeDao;
import cn.graduation.bbs.dao.UserDao;
import cn.graduation.bbs.dto.comment.CommentDTO;
import cn.graduation.bbs.dto.post.PostTypeDTO;
import cn.graduation.bbs.entity.CommentEntity;
import cn.graduation.bbs.entity.PostEntity;
import cn.graduation.bbs.entity.UserEntity;
import cn.graduation.bbs.enums.PostTagsEnum;
import cn.graduation.bbs.enums.StatusCodeEnum;
import cn.graduation.bbs.service.CommentService;
import cn.graduation.bbs.utils.EmptyUtils;
import cn.graduation.bbs.utils.OperUserUtils;
import cn.graduation.bbs.vo.comment.CommentFilter;
import cn.graduation.bbs.vo.comment.CommentVO;
import cn.graduation.bbs.vo.post.PostCommentCountVO;
import cn.graduation.bbs.vo.post.PostTypeFilter;
import cn.graduation.bbs.vo.user.UserCommentCountVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @desc: 评论管理的业务层实现类
 * @author: zyb
 * @since: 2020/4/8 17:11
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private PostTypeDao postTypeDao;

    /**
     * 条件查询评论列表
     *
     * @param commentFilter
     * @return
     */
    @Override
    public WebResponse queryCommentList(CommentFilter commentFilter) {
        if (EmptyUtils.isEmpty(commentFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PageHelper.startPage(commentFilter.getPageNum(), commentFilter.getPageSize());
        List<CommentEntity> commentEntities = commentDao.queryCommentList(genCommentFilter(commentFilter));
        //查询数据为空
        if (commentEntities == null || commentEntities.size() == 0) {
            web.setCode(StatusCodeEnum.DATA_IS_NULL.getCode());
            web.setMessage("未查询到符合条件的评论");
            return web;
        }
        ListPage<CommentEntity> postListPage = new ListPage<>(commentEntities);
        List<CommentVO> list = new ArrayList<>();
        commentEntities.forEach(v -> {
            CommentVO vo = genCommentEntity(v);
            list.add(vo);
        });
        web.setData(new ListPage<>(list, postListPage.getPage()));
        return web;
    }

    /**
     * 根据id集合批量删除评论
     *
     * @param commentFilter
     * @return
     */
    @Override
    public WebResponse delete(CommentFilter commentFilter) {
        if (EmptyUtils.isEmpty(commentFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        if (commentFilter.getIds().size() <= 0) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("请选择要删除的评论");
            return web;
        }
        commentDao.delete(commentFilter.getIds());
        return web;
    }

    /**
     * 根绝id查看评论详情
     *
     * @param commentFilter
     * @return
     */
    @Override
    public WebResponse queryCommentById(CommentFilter commentFilter) {
        if (EmptyUtils.isEmpty(commentFilter) || EmptyUtils.isEmpty(commentFilter.getId())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        CommentEntity commentEntity = commentDao.queryCommentById(commentFilter.getId());
        web.setData(commentEntity);
        return web;
    }

    /**
     * 查找用户评论数量列表
     *
     * @param filter
     * @return
     */
    @Override
    public WebResponse queryUserCommentCountList(PostTypeFilter filter) {
        WebResponse web = new WebResponse();
        PageHelper.startPage(filter.getPageNum(), filter.getPageSize());
        List<UserCommentCountDO> doList = commentDao.queryCommentCountList(filter);
        //防止查询数据为空时控制台报错
        if (doList == null || doList.size() == 0) {
            web.setCode(StatusCodeEnum.DATA_IS_NULL.getCode());
            web.setMessage(StatusCodeEnum.DATA_IS_NULL.getMessage());
            return web;
        }
        ListPage<UserCommentCountDO> doListPage = new ListPage<>(doList);
        List<Integer> userIdList = doList.stream().map(v -> v.getUserId()).collect(Collectors.toList());
        List<UserEntity> userList = userDao.findByUserIdList(userIdList);
        Map<Integer, UserEntity> userMap = userList.stream().collect(Collectors.toMap(UserEntity::getId, Function.identity()));
        List<UserCommentCountVO> voList = new ArrayList<>();
        doList.forEach(v -> {
            UserCommentCountVO vo = new UserCommentCountVO();
            Optional.ofNullable(v.getUserId()).ifPresent(vo::setUserId);
            Optional.ofNullable(v.getCommentCount()).ifPresent(vo::setCommentCount);
            Optional.ofNullable(userMap.get(v.getUserId()).getNickName()).ifPresent(vo::setNickname);
            Optional.ofNullable(userMap.get(v.getUserId()).getPhoto()).ifPresent(vo::setPhoto);
            voList.add(vo);
        });
        web.setData(new ListPage<>(voList, doListPage.getPage()));
        return web;
    }

    /**
     * 查找帖子评论数量列表
     *
     * @param filter
     * @return
     */
    @Override
    public WebResponse queryPostCommentCountList(PostTypeFilter filter) {
        WebResponse web = new WebResponse();
        PageHelper.startPage(filter.getPageNum(), filter.getPageSize());
        List<PostCommentCountDO> doList = commentDao.queryPostCommentCountList(filter);
        //防止查询数据为空时控制台报错
        if (doList == null || doList.size() == 0) {
            web.setCode(StatusCodeEnum.DATA_IS_NULL.getCode());
            web.setMessage(StatusCodeEnum.DATA_IS_NULL.getMessage());
            return web;
        }
        ListPage<PostCommentCountDO> doListPage = new ListPage<>(doList);
        List<Integer> postIdList = doList.stream().map(PostCommentCountDO::getPostId).collect(Collectors.toList());
        List<PostEntity> postList = postDao.findByPostIdList(postIdList);
        Map<Integer, PostEntity> postMap = postList.stream().collect(Collectors.toMap(PostEntity::getId, Function.identity()));
        List<PostCommentCountVO> voList = new ArrayList<>();
        doList.forEach(v -> {
            PostCommentCountVO vo = new PostCommentCountVO();
            Optional.ofNullable(v.getPostId()).ifPresent(vo::setPostId);
            Optional.ofNullable(v.getCommentCount()).ifPresent(vo::setCommentCount);
            Optional.ofNullable(postMap.get(v.getPostId()).getTitle()).ifPresent(vo::setTitle);
            voList.add(vo);
        });
        web.setData(new ListPage<>(voList, doListPage.getPage()));
        return web;
    }

    /**
     * 提交评论
     *
     * @param commentFilter
     * @return
     */
    @Override
    public WebResponse addComment(CommentFilter commentFilter) {
        if (EmptyUtils.isEmpty(commentFilter) || EmptyUtils.isEmpty(commentFilter.getPostId())
                || EmptyUtils.isEmpty(commentFilter.getComment())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        CommentDTO dto = new CommentDTO();
        if (EmptyUtils.isEmpty(OperUserUtils.getUserId())) {
            web.setCode(StatusCodeEnum.ACCESS_ERROR.getCode());
            web.setMessage("请先登录账号,再发表评论");
            return web;
        }
        Optional.ofNullable(OperUserUtils.getUserId()).ifPresent(dto::setUserId);
        Optional.ofNullable(commentFilter.getPostId()).ifPresent(dto::setPostId);
        Optional.ofNullable(commentFilter.getComment()).ifPresent(dto::setComment);
        dto.setCreateTime(new Date());
        commentDao.addComment(dto);
        return web;
    }

    /**
     * 分页查询用户帖子的评论（不包含自己的评论）
     *
     * @param commentFilter
     * @return
     */
    @Override
    public WebResponse queryUserPostCommentList(CommentFilter commentFilter) {
        if (EmptyUtils.isEmpty(commentFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PageHelper.startPage(commentFilter.getPageNum(), commentFilter.getPageSize());
        List<CommentEntity> commentEntities = commentDao.queryUserPostCommentList(OperUserUtils.getUserId());
        //防止查询数据为空时控制台报错
        if (commentEntities == null || commentEntities.size() == 0) {
            web.setCode(StatusCodeEnum.DATA_IS_NULL.getCode());
            web.setMessage(StatusCodeEnum.DATA_IS_NULL.getMessage());
            return web;
        }
        ListPage<CommentEntity> postListPage = new ListPage<>(commentEntities);
        List<CommentVO> list = new ArrayList<>();
        commentEntities.forEach(v -> {
            CommentVO vo = genCommentEntity(v);
            list.add(vo);
        });
        web.setData(new ListPage<>(list, postListPage.getPage()));
        return web;
    }

    /**
     * 修改评论点赞状态
     *
     * @param commentFilter
     * @return
     */
    @Override
    public WebResponse modifyCommentTags(CommentFilter commentFilter) {
        if (EmptyUtils.isEmpty(commentFilter) || EmptyUtils.isEmpty(commentFilter.getId()) || EmptyUtils.isEmpty(commentFilter.getTags())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        Integer userId = OperUserUtils.getUserId();
        //从前端接tags（用户评论状态是已点赞则取消点赞，状态是未点赞则点赞）
        if (commentFilter.getTags().equals(PostTagsEnum.TAGS_YES.getCode())) {
            commentDao.deleteCommentTags(userId, commentFilter.getId());
        } else if (commentFilter.getTags().equals(PostTagsEnum.TAGS_NO.getCode())) {
            commentDao.addCommentTags(userId, commentFilter.getId());
        }
        return web;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testSave(PostTypeDTO dto) {
        postTypeDao.save(dto);
        postTypeDao.testTranslation();
    }

    /**
     * 处理返回给持久层的参数
     *
     * @param filter
     * @return
     */
    private CommentDTO genCommentFilter(CommentFilter filter) {
        CommentDTO dto = new CommentDTO();
        Optional.ofNullable(filter.getUserId()).ifPresent(dto::setUserId);
        Optional.ofNullable(filter.getPostId()).ifPresent(dto::setPostId);
        Optional.ofNullable(filter.getStartTime()).ifPresent(dto::setStartTime);
        Optional.ofNullable(filter.getEndTime()).ifPresent(dto::setEndTime);
        return dto;
    }

    /**
     * 处理返回给前端的参数
     *
     * @param entity
     * @return
     */
    private CommentVO genCommentEntity(CommentEntity entity) {
        CommentVO vo = new CommentVO();
        Optional.ofNullable(entity.getId()).ifPresent(vo::setId);
        Optional.ofNullable(entity.getUserId()).ifPresent(vo::setUserId);
        Optional.ofNullable(entity.getPostId()).ifPresent(vo::setPostId);
        Optional.ofNullable(entity.getComment()).ifPresent(vo::setComment);
        Optional.ofNullable(entity.getCreateTime()).ifPresent(vo::setCreateTime);
        Optional.ofNullable(entity.getTitle()).ifPresent(vo::setTitle);
        Optional.ofNullable(entity.getStatus()).ifPresent(vo::setStatus);
        Optional.ofNullable(entity.getNickName()).ifPresent(vo::setNickName);
        Optional.ofNullable(entity.getPhoto()).ifPresent(vo::setPhoto);
        //如果当前用户已登录，则查询此评论是否被点赞
        if (!EmptyUtils.isEmpty(OperUserUtils.getUserId())) {
            Integer tags = commentDao.queryUserCommentTags(OperUserUtils.getUserId(), entity.getId());
            if (EmptyUtils.isEmpty(tags) || tags == 0) {
                vo.setCommentTags(PostTagsEnum.TAGS_NO.getCode());
            } else {
                vo.setCommentTags(PostTagsEnum.TAGS_YES.getCode());
            }
        }
        //查询评论的点赞数
        Optional.ofNullable(commentDao.queryUserCommentTags(null, entity.getId())).ifPresent(vo::setCommentTagsCount);
        return vo;
    }
}
