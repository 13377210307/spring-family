package com.collections.map.HashMap17;

/**
 * @Author: w
 * @Date: 2021/2/26 14:37
 */
public class Test {

    public static void main(String[] args) {
        HashMapStudy<String, String> hashMap = new HashMapStudy<>();
        hashMap.put("你好你好","你好啊");
        hashMap.put("你好","我不好");
        hashMap.put("你好你好嘿嘿嘿","嘿嘿嘿");
        hashMap.put("你好你好","你好啊测试");
        String value = hashMap.get("你好你好");
        System.out.println(value);
    }
}
