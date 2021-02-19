package com.cache.tools;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: w
 * @Date: 2021/2/19 16:41
 *
 * 布隆过滤器
 *
 * 布隆过滤器是存在误判率的，但是这个误判率可以由我们自己设置，但设置的越小，查询的时间也越长；
 *
 * 布隆过滤器判断不存在的数据就一定不存在，但判断存在的数据就可能存在。
 *
 * 存在误判的原因是因为存在hash冲突，因为某个值经过hash计算后会成为相同的bit位
 *
 * 布隆过滤器底层依赖于bit
 * 1：在调用put方法后，先通过几个不同的hash函数（与容错率有关，hashCode%数组长度）获取bit位，然后将将一个数组中的某几个bit改为1，（由于hash函数不同，所以会得出几个bit值）
 *
 * 布隆过滤器是不能删除的：因为hash冲突的原因，删了某个bit数据，可能会造成其他相同bit位存在数据也被删除
 *
 * 影响误判率的因素
 * 1：hash函数的个数
 * 2：数组长度
 * 3：容错率
 */
public class BloomFilterDemo {

    // 预计插入量
    private static int size = 1000000;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),size,0.0001);

    public static void main(String[] args) {

        for (int i = 1; i < size; i++) {
           bloomFilter.put(i);
        }

        List<Integer> list = new ArrayList<>(10000);

        // 故意取10000个不存在的数据，看看误判率为多少
        for (int i = size ; i < size + 10000; i++) {
            if (bloomFilter.mightContain(i)) {  // 误判
                list.add(i);
            }
        }

        System.out.println("误判数量为："+list.size());

    }
}
