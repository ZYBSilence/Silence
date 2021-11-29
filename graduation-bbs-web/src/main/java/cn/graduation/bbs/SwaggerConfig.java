package cn.graduation.bbs;

import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: Swagger2的配置文件，在项目的启动类的同级文件建立
 * @author: zyb
 * @date: 2021/5/14 11:18
 */
@Configuration
@EnableSwagger2
//是否开启swagger，正式环境一般是需要关闭的（避免不必要的漏洞暴露！），可根据springboot的多环境配置进行设置
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class SwaggerConfig {
    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("cn.graduation.bbs.controller")).paths(PathSelectors.any())
                .build();
    }


    /**
     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("Silence社区 Swagger2 构建 RestFul API")
                // 创建人信息
                .contact(new Contact("zyb", "https://blog.csdn.net/qq_40406380?spm=1000.2115.3001.5343", "1520949225@qq.com"))
                // 版本号
                .version("1.0")
                // 描述
                .description("API 描述")
                .build();
    }

//    @Bean
//    public Docket WebApiConfig(){
//        return  new Docket(DocumentationType.SWAGGER_2)
//                .groupName("webApi")
//                .apiInfo(webApiInfo())
//                .select()
//                .paths(Predicates.not(PathSelectors.regex("/api/admin/.*")))
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))
//                .build();
//    }
//
//    @Bean
//    public Docket adminApiConfig(){
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("adminApi")
//                .apiInfo(adminApiInfo())
//                .select()
//                .paths(Predicates.and(PathSelectors.regex("/api/admin/.*")))
//                .build();
//
//    }
//    private ApiInfo webApiInfo(){
//
//        return new ApiInfoBuilder()
//                .title("网站-API文档")
//                .description("本文档描述了Silence社区接口定义")
//                .version("1.0")
//                .contact(new Contact("zyb", "https://blog.csdn.net/qq_40406380?spm=1000.2115.3001.5343", "1520949225@qq.com"))
//                .build();
//    }
//
//    private ApiInfo adminApiInfo(){
//
//        return new ApiInfoBuilder()
//                .title("后台管理系统-API文档")
//                .description("本文档描述了Silence社区后台管理系统接口定义")
//                .version("1.0")
//                .contact(new Contact("chak", "https://blog.csdn.net/qq_40406380?spm=1000.2115.3001.5343", "1520949225@qq.com"))
//                .build();
//    }
}
