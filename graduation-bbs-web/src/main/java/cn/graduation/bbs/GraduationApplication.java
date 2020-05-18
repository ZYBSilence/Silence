package cn.graduation.bbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * @desc: 启动类
 * @author: zyb
 * @since: 2019/12/20 15:26
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.graduation.bbs.dao"})
@ServletComponentScan("cn.graduation")
@Configuration
public class GraduationApplication{
//public class GraduationApplication extends SpringBootServletInitializer {

//    @Override
////    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
////        return application.sources(GraduationApplication.class);
////    }

    public static void main(String[] args) {
        SpringApplication.run(GraduationApplication.class, args);
    }
}
