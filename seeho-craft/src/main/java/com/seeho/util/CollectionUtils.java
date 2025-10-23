package com.seeho.util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

    private CollectionUtils() {

    }

    /**
     * 从map中取出keys中包含的对象
     *
     * @param keys
     * @param map
     * @return
     */
    public static <T, K> List<T> filter(List<K> keys, Map<K, T> map) {
        List<T> newList = new ArrayList<>();
        keys.forEach(bill -> {
            if (map.get(bill) != null)
                newList.add(map.get(bill));
        });
        return newList;
    }

    /**
     * 获取集合的size如果是null 则返回0
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> int size(Collection<T> collection) {
        if (Objects.isNull(collection)) {
            return 0;
        }
        return collection.size();
    }

    /**
     * 将list根据指定属性分组成多个list
     *
     * @param list
     * @param function
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, List<V>> groupBy(List<V> list, Function<V, K> function) {
        if (list == null)
            return null;
        Map<K, List<V>> map = new HashMap<>();
        for (V v : list) {
            K k = function.apply(v);
            List<V> sub = map.get(k);
            if (sub == null) {
                sub = new ArrayList<>();
                map.put(k, sub);
            }
            sub.add(v);
        }
        return map;
    }

    /**
     * 将集合转成唯一建对应的值 map
     * foo:数据库多行数据，ID唯一，转成map类型数据，1一个ID对应一行数据对象
     *
     * @param list     数据集
     * @param function 唯一建get方法
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> groupById(List<V> list, Function<V, K> function) {
        if (list == null)
            return null;
        return list.stream().collect(Collectors.toMap(function, Function.identity()));
    }


    /**
     * 将list根据指定属性分组成多个list,结果只保留指定的属性
     *
     * @param list
     * @param function1
     * @param function2
     * @return
     */
    public static <K1, K2, V> Map<K1, List<K2>> groupBy(List<V> list, Function<V, K1> function1, Function<V, K2> function2) {
        if (list == null)
            return null;
        Map<K1, List<K2>> map = new HashMap<>();
        for (V v : list) {
            K1 k = function1.apply(v);
            List<K2> sub = map.get(k);
            if (sub == null) {
                sub = new ArrayList<>();
                map.put(k, sub);
            }
            K2 k2 = function2.apply(v);
            sub.add(k2);
        }
        return map;
    }

    /**
     * 将list根据指定属性分组成,并将其中一个属性用指定符号连接起来
     *
     * @param list
     * @param function1
     * @param function2
     * @param separator
     * @param <K>
     * @param <V>
     * @param <T>
     * @return
     */
    public static <K, V, T> Map<K, String> groupBy(List<V> list, Function<V, K> function1, Function<V, T> function2, String separator) {
        if (list == null)
            return null;
        Map<K, List<V>> map = groupBy(list, function1);
        Map<K, String> resultMap = new HashMap<>();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            List<V> list1 = entry.getValue();
            StringBuilder sb = new StringBuilder();
            for (V v : list1) {
                sb.append(",").append(function2.apply(v));
            }
            resultMap.put(entry.getKey(), sb.substring(1));
        }
        return resultMap;
    }

    /**
     * 将集合转成Map ,value使用默认值
     *
     * @param collection
     * @param value
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> toMap(Collection<K> collection, V value) {
        Map<K, V> map = new HashMap<>();
        if (isNotEmpty(collection)) {
            collection.forEach(k -> map.put(k, value));
        }
        return map;
    }

}
