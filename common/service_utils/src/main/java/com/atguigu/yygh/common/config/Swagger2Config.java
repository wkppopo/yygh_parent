package com.atguigu.yygh.common.config;

import com.google.common.base.Predicates;
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

/***
 * 对swagger-ui的页面进行一些中文化和配置
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * 这个插件用于生成 API文档
     * 注入Docket
     *         Docket 是 swagger2 的全局配置对象
     */
    @Bean
    public Docket docket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfo build = new ApiInfoBuilder()
                .title("标题")
                .description("具体的描述.......")
                .contact(new Contact("医院预约挂号设置", "http://localhost:8201/admin/hosp/hospitalSet/findAll", "2219602510@qq.com"))
                .version("1.0")
                .build();

        docket.apiInfo(build);

        docket.select()  //获取Docket 的选择器 返回ApiSelectBuilder
                .apis(RequestHandlerSelectors.basePackage("com.atguigu.yygh.hosp.controller")) //扫描指定包中带有注解的类，生成API文档
                .paths(Predicates.or(   //路径选择器。只有符合正则表达式的路径才会生成文档
                        //PathSelectors.regex("/swagger/.*")//(.)表示任意字符 (*)表示任意数量
                        PathSelectors.regex("/.*")

                ))
                .build();

        return docket;
    }
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Dict")
                .apiInfo(new ApiInfoBuilder()
                        .title("数据字典")
                        .description("前端数据字典.......")
                        .contact(new Contact("数据字典", "http://localhost:8202/admin/hosp/hospitalSet/findAll", "2219602510@qq.com"))
                        .version("1.1")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.atguigu.yygh.cmn.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .apiInfo(new ApiInfoBuilder()
                        .title("数据字典")
                        .description("前端数据字典.......")
                        .contact(new Contact("数据字典", "http://localhost:8203/admin/hosp/hospitalSet/findAll", "2219602510@qq.com"))
                        .version("1.1")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.atguigu.yygh.user.controller"))//扫描指定包中带有注解的类生成文档
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
}
