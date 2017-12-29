package com.ssm.utils;

/**
 * Created by dllo on 17/12/28.
 */
public class AjaxResult {

    private String code;
    private Object message;
    private Object data;

    public AjaxResult() {
    }

    public AjaxResult(String code, Object message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public AjaxResult(Object data) {
        this.code = "0";
        this.message = "success";
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
