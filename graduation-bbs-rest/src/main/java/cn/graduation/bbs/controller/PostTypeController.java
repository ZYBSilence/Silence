package cn.graduation.bbs.controller;

import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.service.PostTypeService;
import cn.graduation.bbs.vo.post.PostTypeFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc: 帖子类型管理
 * @author: zyb
 * @since: 2020/4/7 18:20
 */
@Slf4j
@RestController
@RequestMapping("/api/postType")
public class PostTypeController {
    @Autowired
    private PostTypeService postTypeService;

    @PostMapping("/list")
    public WebResponse queryPostTypeList(@RequestBody PostTypeFilter postTypeFilter) {
        log.info("帖子类型列表 queryPostTypeList 请求参数{" + postTypeFilter + "}");
        return postTypeService.queryPostTypeList(postTypeFilter);
    }

    @PostMapping("/save")
    public WebResponse save(@RequestBody PostTypeFilter postTypeFilter) {
        log.info("新增帖子类型 请求参数{" + postTypeFilter + "}");
        return postTypeService.save(postTypeFilter);
    }

    @PostMapping("/delete")
    public WebResponse delete(@RequestBody PostTypeFilter postTypeFilter) {
        log.info("删除帖子类型 请求参数{" + postTypeFilter + "}");
        return postTypeService.delete(postTypeFilter);
    }

    @PostMapping("/update")
    public WebResponse update(@RequestBody PostTypeFilter postTypeFilter) {
        log.info("修改帖子类型 请求参数{" + postTypeFilter + "}");
        return postTypeService.update(postTypeFilter);
    }

    @PostMapping("/detail")
    public WebResponse queryPostTypeById(@RequestBody PostTypeFilter postTypeFilter) {
        log.info("根据id查询帖子类型 请求参数{" + postTypeFilter + "}");
        return postTypeService.queryPostTypeById(postTypeFilter);
    }
}
