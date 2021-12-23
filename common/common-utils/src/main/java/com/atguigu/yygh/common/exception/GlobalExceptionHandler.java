package com.atguigu.yygh.common.exception;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.result.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody   //将结果以json的方式返回到浏览器
    @ExceptionHandler(Exception.class)  //如果控制器类的方法执行发生错误，就会执行此方法
    public Result error(Exception e){
        e.printStackTrace();
        return Result.build(ResultCodeEnum.FAIL.getCode(),ResultCodeEnum.FAIL.getMessage()); //统一异常处理
    }
}
