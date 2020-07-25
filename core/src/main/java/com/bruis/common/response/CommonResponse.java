package com.bruis.common.response;

/**
 * @author LuoHaiYang
 */
public class CommonResponse {
    /**
     * response信息
     */
    private String message;
    /**
     * response代码
     */
    private Integer code;
    /**
     * response内容
     */
    private Object data;

    /**
     * 通用请求成功返回对象
     * @param data
     * @return
     */
    public static CommonResponse success(Object data) {
        return create(data, ResponseCode.REQUEST_SUCCESS.getMsg(), ResponseCode.REQUEST_SUCCESS.getCode());
    }

    /**
     * 通用请求失败返回对象
     * @param data
     * @return
     */
    public static CommonResponse fail(Object data) {
        return create(data, ResponseCode.REQUEST_FAIL.getMsg(), ResponseCode.REQUEST_FAIL.getCode());
    }

    /**
     * 自定义返回对象
     * @param data
     * @param message
     * @param code
     * @return
     */
    public static CommonResponse create(Object data, String message, Integer code) {
        CommonResponse response = new CommonResponse();
        response.setData(data);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
