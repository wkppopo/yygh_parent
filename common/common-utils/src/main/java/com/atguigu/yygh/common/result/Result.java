package com.atguigu.yygh.common.result;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public Result(){}

    protected static <T> Result<T> build(T data){
        Result<T> tResult = new Result<>();
        if(data!=null){
            tResult.setData(data);
        }
        return tResult;
    }
    public static <T> Result<T> build(T body,ResultCodeEnum resultCodeEnum){
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> Result<T> build(Integer code,String message){
        Result<T> result = build(null);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    public static <T> Result<T> ok(){
        return build(null);
    }

    /**
     *  操作成功
     */
    public static <T> Result<T> ok(T data){
        return build(data,ResultCodeEnum.SUCCESS);
    }

    /**
     * 操作失败
     */
    public static <T> Result<T> fail(){
        return  build(ResultCodeEnum.FAIL.getCode(),ResultCodeEnum.FAIL.getMessage() );
    }
    public static <T> Result<T> fail(T data){
        return build(data,ResultCodeEnum.FAIL);
    }

}
