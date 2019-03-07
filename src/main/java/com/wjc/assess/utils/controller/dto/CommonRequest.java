package com.wjc.assess.utils.controller.dto;

public class CommonRequest {
    public RequestHead request; //请求头
    public Object body;         //请求体

    public RequestHead getRequest() {
        return request;
    }

    public void setRequest(RequestHead request) {
        this.request = request;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
