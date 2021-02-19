package com.cache.service.cachePenetrate;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cache.entity.Orders;

/**
 * @Author: w
 * @Date: 2021/2/19 15:11
 */
public interface CachePenetrateService extends IService<Orders> {


    R getOrderById(Long id);

    R solveBySetNull(Long id);

    R solveByBloomFilter(Long id);
}
