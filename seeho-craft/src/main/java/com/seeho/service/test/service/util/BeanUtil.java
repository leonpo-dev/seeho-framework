package com.seeho.service.test.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seeho.service.test.service.enums.ErrorCode;
import com.seeho.service.test.service.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Leonpo
 * @since 2025-10-23
 */
@Slf4j
public class BeanUtil {
    /**
     * 字段缓存，避免重复反射带来的性能损耗
     */
    private static final Map<Class<?>, List<Field>> FIELD_CACHE = new ConcurrentHashMap<>();

    /**
     * Jackson用于深拷贝
     */
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 单对象拷贝（浅拷贝同名字段）
     */
    public static <T> T copy(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            copyFields(source, target);
            return target;
        } catch (Exception e) {
            log.error("BeanUtil.copy error: {}", e.getMessage());
            throw new BusinessException(e, ErrorCode.BEAN_COPY);
        }
    }

    /**
     * 列表拷贝（浅拷贝）
     */
    public static <S, T> List<T> copyList(Collection<S> sourceList, Class<T> targetClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(sourceList.size());
        for (S s : sourceList) {
            T t = copy(s, targetClass);
            if (t != null) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 深拷贝（使用序列化实现）
     */
    public static <T> T deepCopy(T source) {
        if (source == null) {
            return null;
        }
        try {
            byte[] data = mapper.writeValueAsBytes(source);
            @SuppressWarnings("unchecked")
            T copy = (T) mapper.readValue(data, source.getClass());
            return copy;
        } catch (Exception e) {
            log.error("BeanUtil.deepCopy error: {}", e.getMessage());
            throw new BusinessException(e, ErrorCode.BEAN_COPY);
        }
    }

    /**
     * 反射字段复制（同名字段）
     */
    private static void copyFields(Object source, Object target) throws IllegalAccessException {
        List<Field> sourceFields = getAllFields(source.getClass());
        List<Field> targetFields = getAllFields(target.getClass());

        Map<String, Field> targetFieldMap = new HashMap<>();
        for (Field f : targetFields) {
            f.setAccessible(true);
            targetFieldMap.put(f.getName(), f);
        }

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            Field targetField = targetFieldMap.get(sourceField.getName());
            if (targetField != null &&
                    targetField.getType().isAssignableFrom(sourceField.getType())) {
                Object value = sourceField.get(source);
                targetField.set(target, value);
            }
        }
    }

    /**
     * 获取类及其父类的所有字段并缓存
     */
    private static List<Field> getAllFields(Class<?> clazz) {
        return FIELD_CACHE.computeIfAbsent(clazz, key -> {
            List<Field> fields = new ArrayList<>();
            while (key != null && key != Object.class) {
                fields.addAll(Arrays.asList(key.getDeclaredFields()));
                key = key.getSuperclass();
            }
            return fields;
        });
    }
}
