package com.wjc.assess.controller;

import com.alibaba.fastjson.JSON;
import com.wjc.assess.entity.Totaltime;
import com.wjc.assess.service.impl.TestService;
import com.wjc.assess.utils.dto.CommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//所有后台都用/api路径
@RestController
@RequestMapping("/api")
public class LoginController extends BaseController {
    @Autowired
    private TestService testService;

    @RequestMapping("/Login")
    public Object Login(HttpServletRequest httpServletRequest){
        //获取请求对象
        return true;
    }

    @RequestMapping("/Regist")
    public Object Regist(HttpServletRequest httpServletRequest){
        return true;
    }
}
