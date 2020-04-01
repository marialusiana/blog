package com.example.blog.common.dto.exception;

/**
 * resourceNotFoundException
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -3893065755576061342L;
    private String fieldValue;
    private String fieldName;
    private String resource;

    public ResourceNotFoundException(String fieldValue, String fieldName, String resource) {
        this.fieldValue = fieldValue;
        this.fieldName = fieldName;
        this.resource = resource;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getResource() {
        return resource;
    }
}
