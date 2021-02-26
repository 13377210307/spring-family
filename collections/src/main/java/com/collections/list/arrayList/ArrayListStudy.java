package com.collections.list.arrayList;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: w
 * @Date: 2021/2/26 11:49
 */
public class ArrayListStudy {

    public static void main(String[] args) {

        /**
         * 若没有传入初始容量：会先创建一个没有容量的数组
         *  this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
         *  private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
         */
        List<Integer> arrays =  new ArrayList<>();

        /**
         * 添加数据流程：
         * 1：先调用确定容量方法：ensureCapacityInternal
         */
        arrays.add(1);
        arrays.add(2);
    }
}
