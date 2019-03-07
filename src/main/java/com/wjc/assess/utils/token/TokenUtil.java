package com.wjc.assess.utils.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wjc.assess.entity.Customer;

import java.util.UUID;

/*
token工具类，生成token，解析token
 */
public class TokenUtil {
    //过期时间 默认10分钟
    public static long EXPIRE_TIME = 1000 * 60 * 10;

    /*
     * 生成token,传入用户信息，
     * token作为键，用户信息转成json数据存入redis，并有效时间
     */
    public static String createToken(Customer user){
        //生成token
        String token = UUID.randomUUID().toString();
        //user转json格式
        String userStr = JSON.toJSON(user).toString();
        //放到redis

        return token;
    }

}
