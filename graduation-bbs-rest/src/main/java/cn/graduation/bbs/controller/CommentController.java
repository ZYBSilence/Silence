package cn.graduation.bbs.controller;

import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.service.CommentService;
import cn.graduation.bbs.vo.comment.CommentFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("条件查询评论列表 queryCommentList 请求参数{" + commentFilter + "}");
        return commentService.queryCommentList(commentFilter);
    }

    @PostMapping("/delete")
    public WebResponse delete(@RequestBody CommentFilter commentFilter) {
        log.info("根据id集合批量删除评论 delete 请求参数{" + commentFilter + "}");
        return commentService.delete(commentFilter);
    }

    @PostMapping("/detail")
    public WebResponse queryCommentById(@RequestBody CommentFilter commentFilter) {
        log.info("根绝id查看评论详情 queryCommentById 请求参数{" + commentFilter + "}");
        return commentService.queryCommentById(commentFilter);
    }
}
