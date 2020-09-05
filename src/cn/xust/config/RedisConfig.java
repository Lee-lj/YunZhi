package cn.xust.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.xust.utils.RedisUtil;

//@Configuration
public class RedisConfig {
  
    private String host = "49.232.138.118";

    private int port = 6379;

    private int database = 0;
    @Bean
    public RedisUtil getRedisUtil(){
        RedisUtil redisUtil=new RedisUtil();
        redisUtil.initPool(host,port,database);
        return redisUtil;
    }
}
