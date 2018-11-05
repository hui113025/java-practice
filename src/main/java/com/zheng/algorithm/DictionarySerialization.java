package com.zheng.algorithm;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @author zhenghui
 * @date 2018/11/5
 * 字典序列化
 * 约定：每个字典的key/value不能为 换行、分割、等号
 */
public class DictionarySerialization {

    public static void main(String args[]) {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Map<String, String> stringMap1 = new HashMap<String, String>();
        stringMap1.put("k1", "v1");
        stringMap1.put("k2", "v2");
        mapList.add(stringMap1);
        Map<String, String> stringMap2 = new HashMap<String, String>();
        stringMap2.put("A", "XXX");
        stringMap2.put("B", " ");
        stringMap2.put(" ", "C");
        stringMap2.put(" ", " ");
        mapList.add(stringMap2);

        Object[] strings = mapList.toArray();
        String result = store(strings);
        System.out.println("数组转字符串：" + result);
        String str = "k1=v1;?=#";
        Object[] objects = load(str);
        System.out.println("字符串转数组：" + JSONObject.toJSON(objects));
    }

    /**
     * 把数组保存到⼀个字符串中
     * @param objects
     * @return
     */
    private static String store(Object[] objects) {
        if (objects == null) {
            return null;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            Map<String, String> map = (Map<String, String>) objects[i];
            int j = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                str.append(entry.getKey()).append("=").append(entry.getValue());
                if (j < map.size() - 1) {
                    str.append(";");
                }
                j++;
            }

            if (i < objects.length - 1) {
                str.append("\\n");
            }
        }
        return str.toString();
    }

    /**
     * 把字符串中的内容读取为字典数组
     * @param str
     * @return
     */
    private static Object[] load(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        List<Map<String, String>> mapLinkedList = new LinkedList<Map<String, String>>();
        String[] arrys = str.split("\n");
        for (int i = 0; i < arrys.length; i++) {
            Map linkedHashMap = new LinkedHashMap();
            String[] keyValues = arrys[i].split(";");
            for (int j = 0; j < keyValues.length; j++) {
                String[] keyValue = keyValues[j].split("=");
                if (keyValue.length == 2) {
                    linkedHashMap.put(keyValue[0], keyValue[1]);
                }
            }
            mapLinkedList.add(linkedHashMap);
        }
        return mapLinkedList.toArray();
    }

}
