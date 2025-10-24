package com.seeho.service.test.service.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.seeho.service.test.service.enums.ErrorCode;
import com.seeho.service.test.service.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

/**
 * @author Leonpo
 * @since 2025-10-23
 */
@Slf4j
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // 配置全局行为
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 对象转 JSON 字符串
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("JsonUtil.toJson error: {}", e.getMessage());
            throw new BusinessException(e, ErrorCode.JSON_CONVERSION);
        }
    }

    /**
     * 对象转格式化 JSON 字符串
     */
    public static String toPrettyJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.error("JsonUtil.toPrettyJson error: {}", e.getMessage());
            throw new BusinessException(e, ErrorCode.JSON_CONVERSION);
        }
    }

    /**
     * JSON 字符串转对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("JsonUtil.fromJson error: {}", e.getMessage());
            throw new BusinessException(e, ErrorCode.JSON_CONVERSION);
        }
    }

    /**
     * JSON 字符串转泛型对象
     */
    public static <T> T fromJson(String json, TypeReference<T> typeRef) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return mapper.readValue(json, typeRef);
        } catch (Exception e) {
            log.error("JsonUtil.fromJson (TypeReference) error: {}", e.getMessage());
            throw new BusinessException(e, ErrorCode.JSON_CONVERSION);
        }
    }

    /**
     * 校验字符串是否是合法 JSON
     */
    public static boolean isValidJson(String json) {
        if (json == null || json.isEmpty()) {
            return false;
        }
        try {
            mapper.readTree(json);
            return true;
        } catch (Exception e) {
            throw new BusinessException(e, ErrorCode.JSON_CONVERSION);
        }
    }

}
