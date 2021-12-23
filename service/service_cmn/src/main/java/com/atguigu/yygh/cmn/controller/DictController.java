package com.atguigu.yygh.cmn.controller;

import com.atguigu.yygh.cmn.service.DictService;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.result.ResultCodeEnum;
import com.atguigu.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(tags = "数据字段接口", value = "数据字段接口")
@CrossOrigin //允许跨域注解
@RestController
@RequestMapping(value = "/swagger/admin/cmn/dict")
public class DictController {
    private DictService dictService;  //导入业务类接口
    @Autowired
    public void setDictService(DictService dictService){
        this.dictService=dictService;
    }

    /**
     * 根据父id查询子数据列表
     */
    @ApiOperation(value = "根据父id查询子数据列表")
    @GetMapping("/findChildData/{id}")
    public Result<List<Dict>> findByParentId(@PathVariable long id){
        List<Dict> list=dictService.findByParentId(id);
        return Result.ok(list);
    }


    /**
     * 导出数据字典的接口
     */
    @ApiOperation("导出数据字典的接口")
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response){
        try {
            dictService.exportDictData(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 导入上传的数据字典的接口
     */
    @PostMapping("/importData")
    public Result importData(MultipartFile file){
        try {
            dictService.importDictData(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok(ResultCodeEnum.SUCCESS);
    }

    /**
     * 被远程调用接口
     * 根据dictCode and value 查询
     */
    @GetMapping("/getName/{dictCode}/{value}")
    public String getName(@PathVariable String dictCode,@PathVariable String value){
        System.out.println("接收到hosp的远程调用***********************");
        String dictName=dictService.getDictName(dictCode,value);
        return dictName;
    }
}
