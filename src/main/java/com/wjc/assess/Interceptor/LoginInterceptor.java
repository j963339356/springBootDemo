package com.wjc.assess.Interceptor;

import com.wjc.assess.utils.controller.CommonThreadLocal;
import com.wjc.assess.utils.controller.MessageHelp;
import com.wjc.assess.utils.dto.CommonRequest;
import com.wjc.assess.utils.dto.CommonResponse;
import com.wjc.assess.utils.dto.RequestHead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 登录验证拦截，如果是登录则通过，如果是其他请求则验证token
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
    //返回true继续向下走，返回false立刻中断执行
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("执行拦截器的preHandle方法");
        //创建响应对象，全程都会用到，比如说某个地方可能会出错，就把出错信息写到报文头里，方便找错
        RequestHead requestHead = MessageHelp.getCommonRequest(httpServletRequest).getRequest();
        CommonResponse commonResponse = MessageHelp.createCommonResponse(requestHead.id,requestHead.service);
        CommonThreadLocal.set(commonResponse);

        //如果是登录或注册，就放行
        if(httpServletRequest.getRequestURI().equals("/api/Login") || httpServletRequest.getRequestURI().equals("/api/Regist")){
            return true;
        }
        //否则验证token


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("执行拦截器的postHandle方法");

//        httpServletResponse
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("执行拦截器的afterCompletion方法");
        //释放线程
        CommonThreadLocal.free();
    }
}
