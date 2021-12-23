package com.atguigu.yygh.hosp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.atguigu")  //这条很重要--跨模块代码功能依赖这个注解进行完全扫描
@MapperScan("com.atguigu.yygh.hosp.mapper")  //必须写在主启动类上

@EnableDiscoveryClient  //启动 nacos 注册服务 将此模块注册到nacos上
//指定远程调用接口的基础包
@EnableFeignClients(basePackages = {"com.atguigu.yygh.client"}) //开启远程调用(只有在服务调用端使用这个注解) --会找@FeignClient("service-cmn")这个注解所定义的接口实现调用
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class,args);
    }
}
