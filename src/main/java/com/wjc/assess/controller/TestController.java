package com.wjc.assess.controller;

import com.alibaba.fastjson.JSON;
import com.wjc.assess.entity.Totaltime;
import com.wjc.assess.service.TestInterface;
import com.wjc.assess.utils.controller.dto.CommonRequest;
import com.wjc.assess.utils.redis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//所有后台都用/api路径
@RestController
@RequestMapping("/api")
public class TestController extends BaseController {
    @Autowired
    private TestInterface testDao;
    @Autowired
    private JedisUtil redis;

    @RequestMapping(value = "/a", produces = {"application/json;charset=UTF-8"})
    public Object hello(HttpServletRequest httpServletRequest){
        return testDao.get(0);
    }

    @RequestMapping(value = "/a2")
    public Object get(HttpServletRequest httpServletRequest){
        //获取请求对象
//        CommonRequest request = getCommonRequest(httpServletRequest);
//        Totaltime totaltime = JSON.parseObject(request.body.toString(),Totaltime.class);
        redis.set("xiaofa","666");
        System.out.println(redis.get("xiaofa"));
//        return testDao.get2(1);
        return redis.get("xiaofa")+"\\.//";
    }

    @RequestMapping("/adda2")
    public Object add(HttpServletRequest httpServletRequest){
        //获取请求对象
        CommonRequest request = getCommonRequest(httpServletRequest);
        Totaltime totaltime = JSON.parseObject(request.body.toString(),Totaltime.class);
        testDao.add(totaltime);
        return true;
    }

    @RequestMapping(value = "/b", produces = {"application/json;charset=UTF-8"})
    public Object hello(String str){
        return "Hello World";
    }

}
