package com.atguigu.yygh.cmn.config;

import com.atguigu.yygh.cmn.service.DictService;
import com.atguigu.yygh.cmn.service.impl.DictServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.atguigu.yygh.cmn.mapper") //使用注解配置映射器
public class CmnConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
    /**
     * 给ioc容器注入service对象
     */
    @Bean
    public DictService dictService(){
        return new DictServiceImpl();
    }
}
