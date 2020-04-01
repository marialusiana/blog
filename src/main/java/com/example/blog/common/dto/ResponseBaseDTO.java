package com.example.blog.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ResponseBaseDTO<Any> {

 private boolean status = false;
 
 private String code = "500";
 
 private String message = "internal server error";

 private Any data;

}