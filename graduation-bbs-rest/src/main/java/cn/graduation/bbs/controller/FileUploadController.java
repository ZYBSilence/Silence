package cn.graduation.bbs.controller;


import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.enums.StatusCodeEnum;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;


/**
 * @desc: 文件上传
 * @author: zyb
 * @since: 2020/4/21 17:51
 */
@Slf4j
@RestController
@RequestMapping("/api/oss")
@CrossOrigin
public class FileUploadController {
    /**
     * 上传文件
     *
     * @return
     */
    @PostMapping("/upload")
    public WebResponse uploadTeacherImg(@RequestParam("file") MultipartFile file) {
        // 地域节点
        String endpoint = "oss-cn-shenzhen.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAI4Fh5GSwdwCWne6YKBCuf";
        String accessKeySecret = "8mOPtAlBDTwzKRq5IuFvOAxVR6YDCc";
        // BucketName
        String yourBucketName = "zybsilence";

        WebResponse web = new WebResponse();
        try {
            // 1.获取上传文件 MultipartFile file
            // @RequestParam("file") file 与表单输入项的name值保持一致

            // 2.获取上传文件名称，获取上传文件输入流
            String fileName = file.getOriginalFilename();
            // 在文件名称之前添加uuid值，保证文件名称不重复
            String uuid = UUID.randomUUID().toString();
            fileName = uuid + fileName;

            // 获取当前日期
            String filePath = new DateTime().toString("yyyy-MM-dd");

            // 拼接文件完整路径
            fileName = "silence/postPicture/" + filePath + "/" + fileName;

            // 获取上传文件输入流
            InputStream in = file.getInputStream();

            // 3.把上传文件存储到阿里云oss里面
            // 创建OSSClient实例。
            OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流
            // 第一个参数时BucketName，第二个参数是文件名称，第三个参数是输入流
            ossClient.putObject(yourBucketName, fileName, in);

            // 关闭OSSClient。
            ossClient.shutdown();

            // 返回上传之后的oss存储路径
            String path = "http://" + yourBucketName + "." + endpoint + "/" + fileName;

            web.setData(path);
            return web;
        } catch (Exception e) {
            e.printStackTrace();
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage(StatusCodeEnum.ERROR.getMessage());
            return web;
        }
    }
}
