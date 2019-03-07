package com.wjc.assess.utils.dto;

public class CommonResponse {
    public ResponseHead response; //响应头
    public Object body;         //响应体

    public ResponseHead getResponseHead() {
        return response;
    }

    public void setResponse(ResponseHead response) {
        this.response = response;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
