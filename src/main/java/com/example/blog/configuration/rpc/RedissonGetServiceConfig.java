package com.example.blog.configuration.rpc;

import com.example.blog.log.service.SlmService;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonGetServiceConfig {

    /**
     * Spring boot sudah melakukan auto config terhadap connection redis
     */
    @Autowired
    private RedissonClient redisson;

    /**
     * Untuk ambil service dari "server side" dibutuhkan bean yang 
     * dapat ambil service tersebut dari instance redis
     * @return LicenseService
     */
    @Bean
    public SlmService registerLicenseService() {
        return redisson.getRemoteService().get(SlmService.class);
    }
    
}