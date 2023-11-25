package com.spring.shopping.util;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseRestful <T>{
    /**
     * state code
     */
    private Integer code;
    /**
     * error msg
     */
    private String msg;
    /**
     * output of the query
     */
    private T data;
    public ResponseRestful(Integer code, String msg) {
        this.code =code;
        this.msg = msg;
    }
    private ResponseRestful(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public ResponseRestful(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
