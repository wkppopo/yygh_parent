package com.atguigu.yygh.common.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(20000,"成功"),
    FAIL(201,"失败"),
    PARAM_ERROR(202,"参数不正确"),
    SERVICE_ERROR(203,"服务异常"),
    DATA_ERROR(204,"数据异常"),
    DATA_UPDATE_ERROR(205,"数据更新异常"),

    LOGIN_AUTH(208,"未登录"),
    PERMISSION(209,"没有权限"),

    CODE_ERROR(210,"验证码错误"),
    LOGIN_DISABLED_ERROR(212,"该用户已被禁用"),
    REGISTER_MOBLE_ERROR(213,"手机号已被使用"),
    LOGIN_AURH(214,"需要登陆"),
    LOGIN_ACL(215,"没有权限"),

    URL_ENCODE_ERROR(216,"url错误"),
    ILLEGAL_CALLBACK_REQUEST_ERROR(217,"非法的回调请求"),
    FETCH_ACCESS_TOKEN_FAILED(218,"获取accessToken失败"),
    FETCH_USERINFO_ERROR(219,"获取用户信息失败"),

    PAY_RUN(220,"支付中..."),
    CANCEL_ORDER_FAILED(225,"取消订单失败"),
    CANCEL_ORDER_NO(226,"不能取消预约"),
    HOSCODE_EXIST(230,"医院编号已经存在"),
    NUMBER_NO(240,"可预约号不足"),

    TIME_NO(250,"当前时间不可预约")
    ;
    private final int code;
    private final String message;

    ResultCodeEnum(int code,String message){
        this.code=code;
        this.message=message;
    }

}
