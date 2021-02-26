package com.collections.map.HashMap17;

/**
 * @Author: w
 * @Date: 2021/2/26 14:10
 * hashMap节点类
 */
public class HashEntry<K,V> implements Entry<K,V> {

    public K k;

    public V v;

    public Integer hashCode;

    public HashEntry<K,V> next;

    public HashEntry(K k, V v, Integer hashCode, HashEntry<K, V> next) {
        this.k = k;
        this.v = v;
        this.hashCode = hashCode;
        this.next = next;
    }

    @Override
    public K getKey() {
        return k;
    }

    @Override
    public V getValue() {
        return v;
    }
}
