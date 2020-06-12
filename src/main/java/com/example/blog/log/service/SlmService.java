package com.example.blog.log.service;

import com.example.blog.log.dto.request.SystemLogRequestDto;

public interface SlmService {
    
    public String getMessage();
    public Boolean createLooging(SystemLogRequestDto dataLog);

}