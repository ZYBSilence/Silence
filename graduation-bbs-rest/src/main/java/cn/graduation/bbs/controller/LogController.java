package cn.graduation.bbs.controller;

import cn.graduation.bbs.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc: 测试log4j+slf4j配置
 * @author: zyb
 * @since: 2020/1/2 14:58
 */
@Slf4j
@RestController
@RequestMapping
public class LogController {

    @GetMapping("/getLogTest")
    public String getTest(){
        log.info("测试方法");
        return "HelloWorld";
    }

    @GetMapping("/getLogPerson")
    public UserEntity person() {
        log.info("查询信息开始-------");
        UserEntity personDto = new UserEntity();
        personDto.setNickName("张");
        personDto.setId(1);
        log.info("查询信息结束-------");
        return personDto;
    }
}