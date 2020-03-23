package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ResponseBaseDTO<Any> {

 private boolean status = false;
 
 private String code = "500";
 
 private String message = "internal server error";

 private Any data;

 public boolean isStatus() {
     return status;
 }

 public void setStatus(boolean status) {
     this.status = status;
 }

 public String getCode() {
     return code;
 }

 public void setCode(String code) {
     this.code = code;
 }

 public String getMessage() {
     return message;
 }

 public void setMessage(String message) {
     this.message = message;
 }

 public Any getData() {
     return data;
 }

 public void setData(Any data) {
     this.data = data;
 }
}