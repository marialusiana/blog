package com.example.blog.controller;

import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.exception.InternalServerErrorException;
import com.example.blog.model.Author;
import com.example.blog.service.LogService;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.blog.log.service.SlmService;

@RestController
public class TestController {

    // @Autowired
    // PrincipalExtractor extractor;

    @Autowired
    private LogService loggingService;

    @Autowired
    private SlmService slmService;

    @GetMapping(value = "/check")
    public ResponseEntity testApi() {
        return new ResponseEntity(new BaseResponseDTO<>(true, "200", "Success", null), HttpStatus.OK);
    }

    @GetMapping(value = "/remote")
    public Object getRemote() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Author userSession = (Author) auth.getPrincipal();

        return userSession;
    }

    @GetMapping(value = "/check-session")
    public ResponseEntity checkSession() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getPrincipal().toString();

            return new ResponseEntity(new BaseResponseDTO<>(true, "200", "Success", username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new BaseResponseDTO<>(false, "500", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/logs")
    public String getTestLog() {
        try {
            System.out.println("masukkkkkkkkk");
            loggingService.createLooging("test", "getTest", "200", "Test create", "Test from Controller", "0");
            // slmService.getMessage();
            return "Log Create";
        } catch (Exception e) {
            System.out.println("gagaaalllll");
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GetMapping(value="/message")
    public ResponseEntity getMessage() {
        return new ResponseEntity(new BaseResponseDTO<>(true, "200", "Success", null), HttpStatus.OK);
    }


}
