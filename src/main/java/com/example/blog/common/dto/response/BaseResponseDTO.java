package com.example.blog.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseDTO<T> {

    private boolean status;
    private String code;
    private String message;
    private T data;

    public static BaseResponseDTO error(String code, String message) {
        return new BaseResponseDTO<>(false, code, message, null);
    }

    public static BaseResponseDTO ok() {
        return new BaseResponseDTO<>(true, "200", "success", null);
    }

    public static <I> BaseResponseDTO<I> ok(I body) {
        return new BaseResponseDTO<I>(true, "200", "success", body);
    }

    public static BaseResponseDTO created() {
        return new BaseResponseDTO<>(true, "201", "created", null);
    }

    public static BaseResponseDTO created(String uri) {
        BaseResponseDTO<Map> baseResponse = new BaseResponseDTO<>();
        baseResponse.setStatus(true);
        baseResponse.setCode("201");
        baseResponse.setMessage("created");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("uri", uri);
        baseResponse.setData(map);
        return baseResponse;
    }

}
