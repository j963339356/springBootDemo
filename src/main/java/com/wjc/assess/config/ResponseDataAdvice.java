package com.wjc.assess.config;

import com.wjc.assess.utils.controller.CommonThreadLocal;
import com.wjc.assess.utils.dto.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/*
拦截返回的数据，添加报文头
 */
@ControllerAdvice
public class ResponseDataAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        CommonResponse response = CommonThreadLocal.getCommonResponse();
        response.setBody(o);
        return response;
    }
}
