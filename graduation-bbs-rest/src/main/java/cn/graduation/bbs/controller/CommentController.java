package cn.graduation.bbs.controller;

import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.service.CommentService;
import cn.graduation.bbs.vo.comment.CommentFilter;
import cn.graduation.bbs.vo.post.PostTypeFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc: 评论管理
 * @author: zyb
 * @since: 2020/4/8 17:04
 */
@Slf4j
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/list")
    public WebResponse queryCommentList(@RequestBody CommentFilter commentFilter) {
        log.info("条件查询评论列表 queryCommentList 请求参数 {}", commentFilter);
        return commentService.queryCommentList(commentFilter);
    }

    @PostMapping("/delete")
    public WebResponse delete(@RequestBody CommentFilter commentFilter) {
        log.info("根据id集合批量删除评论 delete 请求参数 {}", commentFilter);
        return commentService.delete(commentFilter);
    }

    @PostMapping("/detail")
    public WebResponse queryCommentById(@RequestBody CommentFilter commentFilter) {
        log.info("根绝id查看评论详情 queryCommentById 请求参数 {}", commentFilter);
        return commentService.queryCommentById(commentFilter);
    }

    @PostMapping("/count/user/list")
    public WebResponse queryUserCommentCountList(@RequestBody PostTypeFilter filter) {
        log.info("查找用户评论数量列表 queryUserCommentCountList 请求参数 {}", filter);
        return commentService.queryUserCommentCountList(filter);
    }

    @PostMapping("/count/post/list")
    public WebResponse queryPostCommentCountList(@RequestBody PostTypeFilter filter) {
        log.info("查找帖子评论数量列表 queryPostCommentCountList 请求参数 {}", filter);
        return commentService.queryPostCommentCountList(filter);
    }

    @PostMapping("/add")
    public WebResponse addComment(@RequestBody CommentFilter commentFilter) {
        log.info("提交评论 addComment 请求参数 {}", commentFilter);
        return commentService.addComment(commentFilter);
    }

    @PostMapping("/userPostComment/list")
    public WebResponse queryUserPostCommentList(@RequestBody CommentFilter commentFilter) {
        log.info("分页查询用户帖子的评论（不包含自己的评论） queryUserPostCommentList 请求参数 {}", commentFilter);
        return commentService.queryUserPostCommentList(commentFilter);
    }

    @PostMapping("/tags/modify")
    public WebResponse modifyCommentTags(@RequestBody CommentFilter commentFilter) {
        log.info("修改评论点赞状态 modifyCommentTags 请求参数 {}", commentFilter);
        return commentService.modifyCommentTags(commentFilter);
    }
}
