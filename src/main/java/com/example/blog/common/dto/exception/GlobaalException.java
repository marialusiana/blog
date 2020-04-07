package com.example.blog.common.dto.exception;

import javax.servlet.http.HttpServletRequest;

import com.example.blog.common.dto.response.BaseResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * GlobaalException
 */
@Slf4j
@ControllerAdvice
public class GlobaalException {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<BaseResponseDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        if(e.getResource() == null && e.getFieldName() == null && e.getFieldValue() == null){
            BaseResponseDTO errorResponse = BaseResponseDTO.error("400", "Data not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        BaseResponseDTO errorResponse = BaseResponseDTO.error("400", e.getResource() + " with " + e.getFieldName() + " " + e.getFieldValue() + " not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}