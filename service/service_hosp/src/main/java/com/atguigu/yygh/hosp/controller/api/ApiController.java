package com.atguigu.yygh.hosp.controller.api;


import com.atguigu.yygh.common.helper.HttpRequestHelper;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.result.ResultCodeEnum;
import com.atguigu.yygh.hosp.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/hosp") //这个路径不要暴露给医院挂号平台
public class ApiController {
    private HospitalService hospitalService; //控制器类依赖服务接口
    @Autowired
    public void setHospitalService(HospitalService hospitalService){
        this.hospitalService=hospitalService;
    }

    //上传医院接口
    @PostMapping("/saveHospital")
    public Result saveHosp(HttpServletRequest request){
        System.out.println("进入医院api接收*************");
        //获取传递过来的医院信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> stringObjectMap = HttpRequestHelper.switchMap(parameterMap);
        //调用service的方法
        hospitalService.save(stringObjectMap);
        return Result.ok(ResultCodeEnum.SUCCESS);
    }
}
