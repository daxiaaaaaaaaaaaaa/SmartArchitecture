package com.architecturedemo.base;

/**
 * 服务器返回公共实体
 *
 * @param <T>
 * @author weishixiong
 * @Time 2018-03-19
 */
public class BaseDto<T> {
    private int code;
    private String msg;
    private T data;
    private String resParam ;//某响应参数 number

    public String getResParam() {
        return resParam;
    }

    public void setResParam(String resParam) {
        this.resParam = resParam;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
