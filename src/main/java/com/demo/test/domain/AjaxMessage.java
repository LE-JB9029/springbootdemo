package com.demo.test.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.HashMap;
import java.util.Map;

public class AjaxMessage {
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAIL = "fail";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_WARN = "warn";
    private String status;
    private Object data;
    private String message;

    public AjaxMessage(String status) {
        this.status = status;
    }

    @JSONField(
            serialize = false
    )
    public boolean isSuccess() {
        return STATUS_SUCCESS.equals(this.getStatus());
    }

    public static AjaxMessage success(Object data) {
        AjaxMessage am = new AjaxMessage(STATUS_SUCCESS);
        am.setData(data);
        return am;
    }

    public static AjaxMessage success() {
        AjaxMessage am = new AjaxMessage(STATUS_SUCCESS);
        return am;
    }

    public static AjaxMessage failue(Map<String, String> errors) {
        AjaxMessage am = new AjaxMessage(STATUS_FAIL);
        Map<String, Map<String, String>> data = new HashMap(1);
        data.put(STATUS_ERROR, errors);
        am.setData(data);
        return am;
    }

    public static AjaxMessage error(String message) {
        AjaxMessage am = new AjaxMessage(STATUS_ERROR);
        am.setMessage(message);
        return am;
    }

    public static AjaxMessage warn(String message) {
        AjaxMessage am = new AjaxMessage(STATUS_WARN);
        am.setMessage(message);
        return am;
    }

    public AjaxMessage data(Object data) {
        this.data = data;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public AjaxMessage setStatus(String status) {
        this.status = status;
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public AjaxMessage setData(Object data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public AjaxMessage setMessage(String message) {
        this.message = message;
        return this;
    }
}
