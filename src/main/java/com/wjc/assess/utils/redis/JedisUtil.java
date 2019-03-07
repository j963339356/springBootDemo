package com.wjc.assess.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * redis辅助类
 */
@Component
public class JedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key,String value){
        ValueOperations redis = redisTemplate.opsForValue();
        redis.set(key,value);
    }

    public Object get(String key){
        ValueOperations redis = redisTemplate.opsForValue();
        return redis.get(key);
    }

}
