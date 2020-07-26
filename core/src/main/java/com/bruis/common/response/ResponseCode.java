package com.bruis.common.response;

import org.springframework.http.ResponseCookie;

/**
 * @author LuoHaiYang
 */
public enum ResponseCode {
    // 通用类型
    // 请求成功
    REQUEST_SUCCESS(20000, "请求成功"),
    REQUEST_FAIL(20001, "请求失败"),
    PARAMETER_VALIDATION_ERROR(20002, "参数不合法"),
    UNKNOWN_ERROR(20003, "未知错误"),

    // 用户授权类型错误
    USER_NOT_LOGIN(30001, "用户未登录"),
    USER_LOGIN_FAIL_BAD_CREDENTIAL(30002, "用户登录失败，密码错误！"),
    USER_LOGIN_FAIL_UNKNOWN_USERNAME(30003, "用户登录失败，未知用户！"),
    USER_LOGIN_FAIL_LOCKED(30004, "用户登录失败，用户已被锁定！");

    // 业务类型异常

    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
