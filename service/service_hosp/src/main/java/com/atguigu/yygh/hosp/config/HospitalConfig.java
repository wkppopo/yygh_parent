package com.atguigu.yygh.hosp.config;

import com.atguigu.yygh.hosp.service.HospitalService;
import com.atguigu.yygh.hosp.service.HospitalSetService;
import com.atguigu.yygh.hosp.service.impl.HospitalServiceImpl;
import com.atguigu.yygh.hosp.service.impl.HospitalSetServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HospitalConfig {

    //手动注入业务实现类对象
    @Bean
    public HospitalSetService hospitalSetService(){
        return new HospitalSetServiceImpl();
    }

    /**
     * 乐观锁插件 mybatisPlus
     *      * 乐观锁拦截器--用于版本控制
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页插件 mybatisPlus
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
