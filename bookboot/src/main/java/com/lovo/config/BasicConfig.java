package com.lovo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class BasicConfig  {

    @Bean
     public Jedis sprinJedis(){
         return  new Jedis("127.0.0.1",8001);
     }
}
