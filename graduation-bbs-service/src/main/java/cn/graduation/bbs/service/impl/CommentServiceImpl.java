package cn.graduation.bbs.service.impl;

import cn.graduation.bbs.bean.PostCommentCountDO;
import cn.graduation.bbs.bean.UserCommentCountDO;
import cn.graduation.bbs.common.GradException;
import cn.graduation.bbs.common.ListPage;
import cn.graduation.bbs.common.Page;
import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.dao.CommentDao;
import cn.graduation.bbs.dao.PostDao;
import cn.graduation.bbs.dao.UserDao;
import cn.graduation.bbs.dto.comment.CommentDTO;
import cn.graduation.bbs.entity.CommentEntity;
import cn.graduation.bbs.entity.PostEntity;
import cn.graduation.bbs.entity.UserEntity;
import cn.graduation.bbs.enums.StatusCodeEnum;
import cn.graduation.bbs.service.CommentService;
import cn.graduation.bbs.utils.EmptyUtils;
import cn.graduation.bbs.vo.comment.CommentFilter;
import cn.graduation.bbs.vo.comment.CommentVO;
import cn.graduation.bbs.vo.post.PostCommentCountVO;
import cn.graduation.bbs.vo.user.UserCommentCountVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
     * @param page
     * @return
     */
    @Override
    public WebResponse queryUserCommentCountList(Page page) {
        WebResponse web = new WebResponse();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<UserCommentCountDO> doList = commentDao.queryCommentCountList();
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
     * @param page
     * @return
     */
    @Override
    public WebResponse queryPostCommentCountList(Page page) {
        WebResponse web = new WebResponse();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<PostCommentCountDO> doList = commentDao.queryPostCommentCountList();
        ListPage<PostCommentCountDO> doListPage = new ListPage<>(doList);
        List<Integer> postIdList = doList.stream().map(v -> v.getPostId()).collect(Collectors.toList());
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
        return vo;
    }
}