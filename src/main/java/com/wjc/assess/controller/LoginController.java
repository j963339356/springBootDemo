package com.wjc.assess.controller;

import com.wjc.assess.service.impl.TestService;
import com.wjc.assess.utils.controller.dto.CommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

public class LoginController extends BaseController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    public Object Login(HttpServletRequest httpServletRequest){
        //获取请求对象
        return true;
    }

    @RequestMapping(value = "/Regist",method = RequestMethod.POST)
    public Object Regist(HttpServletRequest httpServletRequest){
        return true;
    }
}
