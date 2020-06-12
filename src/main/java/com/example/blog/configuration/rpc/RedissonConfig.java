package com.example.blog.configuration.rpc;

import org.redisson.api.RedissonClient;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    /**
     * Ini merupakan bean untuk default connection dari reddison
     * semua connection sudah di handle secara default oleh Jedis dan Redisson
     * 
     * @param client
     * @return
     */
    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient client) {
        return new RedissonConnectionFactory(client);
    }
}