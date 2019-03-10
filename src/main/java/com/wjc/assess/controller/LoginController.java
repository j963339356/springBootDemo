package com.wjc.assess.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wjc.assess.Enum.ExceptionEnum;
import com.wjc.assess.Exception.CustomException;
import com.wjc.assess.entity.Account;
import com.wjc.assess.service.impl.TestService;
import com.wjc.assess.utils.controller.CommonThreadLocal;
import com.wjc.assess.utils.controller.MessageHelp;
import com.wjc.assess.utils.controller.dto.QueryList;
import com.wjc.assess.utils.redis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

//所有后台都用/api路径
@RestController
@RequestMapping("/api")
public class LoginController extends BaseController {
    @Autowired
    private TestService dao;
    @Autowired
    private JedisUtil redis;

    @PostMapping(value = "/Login")
    public Object Login(HttpServletRequest httpServletRequest) {
        //获取请求对象
        JSONObject jsonObject = (JSONObject) CommonThreadLocal.getCommonResponse().getBody();
        Account login = JSON.parseObject(jsonObject.toString(), Account.class);

        //判断是否存在用户
        Account account = dao.login(login);

        //把用户信息放到缓存，并返回token
        String token = UUID.randomUUID().toString();
        redis.set(token, JSON.toJSON(account).toString());
        return MessageHelp.Result(token);
    }

    @PostMapping(value = "/Regist")
    public Object Regist(HttpServletRequest httpServletRequest){
        //获取请求对象
        JSONObject jsonObject = (JSONObject) CommonThreadLocal.getCommonResponse().getBody();
        Account regist = JSON.parseObject(jsonObject.toString(), Account.class);

        //注册账号
        dao.regist(regist);
        return MessageHelp.Result(true);
    }

    @PostMapping(value = "/getList")
    public Object getList(HttpServletRequest httpServletRequest) {
        //获取请求对象
        JSONObject jsonObject = (JSONObject) CommonThreadLocal.getCommonResponse().getBody();
        QueryList query = JSON.parseObject(jsonObject.toString(), QueryList.class);
        Account account = JSON.parseObject(query.getQueryData().toString(),Account.class);
        int page = query.page;
        int size = query.size;

        return MessageHelp.Result(dao.getList(account,page,size));
    }
}
