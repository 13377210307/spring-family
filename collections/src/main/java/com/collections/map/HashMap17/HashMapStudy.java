package com.collections.map.HashMap17;

/**
 * @Author: w
 * @Date: 2021/2/26 12:06
 *
 * hashMap实现类：数组+链表
 */
public class HashMapStudy<K,V> implements Map<K,V> {

    // 创建数组
    private HashEntry<K,V> table[] = null;

    /**
     * 初始化容量
     */
    public HashMapStudy() {
        table = new HashEntry[16];
    }

    /**
     * put方法
     * 1：通过hash算法获取k值在数组中的位置
     * 2：数组下标值不存在对象：直接插入
     * 3：数组下标值存在对象：将当前数插入数组然后将原数组数据作为链表下一个节点
     *
     * 采用头插法：新添加的数据都放到数组中，若两个key值相同，他不会进行覆盖。
     */
    @Override
    public void put(K k, V v) {
        // 获取下标
        Integer index = this.hash(k);
        table[index] = new HashEntry<>(k,v,k.hashCode(),table[index]);
        /*// 获取数组中的值
        if (table[index] == null) {
            // 将数据插入到数组中
            table[index] = new HashEntry<>(k,v,k.hashCode(),null);
        }else {
            // 将原数组数据作为下一个节点放入
            table[index] = new HashEntry<>(k,v,k.hashCode(),table[index]);
        }*/
    }

    /**
     * 获取值方法
     * 1：根据hash算法获取下标值
     * 2：根据下标值判断数组数据是否存在
     * 3：不存在：返回不存在
     * 4：存在，根据k值判断两者是否相同，相同直接返回
     * 5：不相同，看该数是否存在链表节点：不存在，直接返回不存在
     * 6：存在，继续4，5操作
     */
    @Override
    public V get(K k) {
        // 获取下标
        Integer index = this.hash(k);
        Entry<K,V> entry = this.getData(table[index],k);
        return entry != null ? entry.getValue() : null;
    }

    /**
     * hash算法：通过k值的hashCode进行取余
     */
    private Integer hash(K k) {
        Integer index = k.hashCode() % 16;
        return index > 0 ? index : -index;
    }

    /**
     * 查询k值
     * 1：数组上的数据；2：k值
     */
    private Entry<K,V> getData(HashEntry<K,V> table,K k) {
        // 判断数组数据的k值是否与查询k值一致
        if(k.equals(table.getKey()) || k == table.getKey()) {
            // k值一致
            return table;
        }else {
            // k值不一致
            if(table.next != null) {
                this.getData(table.next,k);
            }
        }
        return null;
    }



}
