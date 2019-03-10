package com.wjc.assess.Interceptor;

import com.wjc.assess.Exception.CustomException;
import com.wjc.assess.Enum.ExceptionEnum;
import com.wjc.assess.utils.controller.CommonThreadLocal;
import com.wjc.assess.utils.controller.MessageHelp;
import com.wjc.assess.utils.controller.dto.CommonRequest;
import com.wjc.assess.utils.controller.dto.CommonResponse;
import com.wjc.assess.utils.controller.dto.RequestHead;
import com.wjc.assess.utils.redis.JedisUtil;
import com.wjc.assess.utils.token.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private JedisUtil redis;
    @Autowired
    private TokenUtil tokenUtil;
    //返回true继续向下走，返回false立刻中断执行
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //设置响应的字符编码格式
        httpServletResponse.setContentType("text/html;charset=utf-8");

        CommonRequest creq = MessageHelp.getCommonRequest(httpServletRequest);
        //如果creq为空，说明报文不符合格式
        if(creq == null){
            CommonResponse commonResponse = MessageHelp.createCommonResponse("","");
            CommonThreadLocal.set(commonResponse);
            throw new CustomException(ExceptionEnum.BUSINESS.getCode(),"数据格式不正确！没有找到数据");
        }

        //创建响应对象，全程都会用到，比如说某个地方可能会出错，就把出错信息写到报文头里，方便找错
        RequestHead requestHead = creq.getRequest();
        CommonResponse commonResponse = MessageHelp.createCommonResponse(requestHead.id,requestHead.service);
        commonResponse.setBody(creq.getBody());
        CommonThreadLocal.set(commonResponse);

        //如果是登录或注册，就放行
        if(httpServletRequest.getRequestURI().equals("/api/Login") || httpServletRequest.getRequestURI().equals("/api/Regist")){
            return true;
        }
        //否则验证token
        String token = tokenUtil.checkToken(httpServletRequest);

        //如果存在token
        if(!redis.hasKey(token)){
            throw new CustomException(ExceptionEnum.BUSINESS.getCode(),"用户信息已过期，请重新登录");
        }
//        httpServletRequest.setAttribute(token,redis.get(token));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("执行拦截器的afterCompletion方法");
        //释放线程
        CommonThreadLocal.free();
    }
}
