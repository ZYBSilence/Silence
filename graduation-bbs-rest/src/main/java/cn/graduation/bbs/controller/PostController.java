package cn.graduation.bbs.controller;

import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.service.PostService;
import cn.graduation.bbs.vo.post.PostFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc: 帖子管理
 * @author: zyb
 * @since: 2020/4/2 11:43
 */
@Slf4j
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/list")
    public WebResponse queryPostList(@RequestBody PostFilter postFilter) {
        log.info("根据条件分页查询帖子列表 queryPostList 请求参数{" + postFilter + "}");
        return postService.queryPostList(postFilter);
    }

    @PostMapping("/detail")
    public WebResponse queryPostById(@RequestBody PostFilter postFilter) {
        log.info("根绝id查看帖子详情 queryPostById 请求参数{" + postFilter + "}");
        return postService.queryPostById(postFilter);
    }

    @PostMapping("/modify/recommend")
    public WebResponse modifyRecommend(@RequestBody PostFilter postFilter) {
        log.info("修改帖子推荐状态 modifyRecommend 请求参数{" + postFilter + "}");
        return postService.modifyRecommend(postFilter);
    }

    @PostMapping("/modify/status")
    public WebResponse modifyStatus(@RequestBody PostFilter postFilter) {
        log.info("修改帖子封禁状态 modifyStatus 请求参数{" + postFilter + "}");
        return postService.modifyStatus(postFilter);
    }

    @PostMapping("/banned")
    public WebResponse bannedPost(@RequestBody PostFilter postFilter) {
        log.info("批量封禁帖子 bannedPost 请求参数{" + postFilter + "}");
        return postService.bannedPost(postFilter);
    }

    @PostMapping("/delete")
    public WebResponse delete(@RequestBody PostFilter postFilter) {
        log.info("根据id集合批量删除帖子 delete 请求参数{" + postFilter + "}");
        return postService.delete(postFilter);
    }

    @PostMapping("/unBan")
    public WebResponse unBanPost(@RequestBody PostFilter postFilter) {
        log.info("批量解封帖子 unBanPost 请求参数{" + postFilter + "}");
        return postService.unBanPost(postFilter);
    }
}
