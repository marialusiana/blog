package com.example.blog.service;

public interface LogService {
    public Boolean createLooging(String type, String method, String code, String message, String description, String time);
}