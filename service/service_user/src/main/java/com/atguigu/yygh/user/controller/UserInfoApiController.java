package com.atguigu.yygh.user.controller;


import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.user.service.UserInfoService;
import com.atguigu.yygh.vo.user.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Map;

@RestController
@RequestMapping("/api/user")

@Api(tags = "用户登陆接口控制器")
public class UserInfoApiController {
    @Autowired
    private UserInfoService userInfoService;

    //用户手机号登陆接口
    @ApiOperation("用户登陆接口")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo){
        Map<String,Object> info=userInfoService.loginUser(loginVo);
        return Result.ok(info);
    }
}
