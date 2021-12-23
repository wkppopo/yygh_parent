package com.atguigu.yygh.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用于远程调用接口
 *
 */
@FeignClient(name = "service-cmn") //被调用的服务名(在nacos中暴露的服务名)(必须写name= 否则报错，找不到service-cmn)
public interface DictFeignClient {

    /**
     * --cmn中被调用的接口方法
     *
     * 根据dictCode and value 查询
     *      @PathVariable("dictCode") :这个要注意，远程调用过程中 必须指定参数的名称(其他地方使用是不需要指定名称的)
     */
    @GetMapping("/swagger/admin/cmn/dict/getName/{dictCode}/{value}")//被调用的接口方法路径要包含controller的路径
    String getName(@PathVariable("dictCode") String dictCode, @PathVariable("value") String value);
}
