package com.tu.sso.common.model;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/30 09:52
 * @Description:
 */
public class Response {

    private String message;

    private Object object;

    private String code;

    public Response(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public Response() {
    }

    public Response(String message, Object object, String code) {
        this.message = message;
        this.object = object;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
