package com.cache.service.cachePuncture;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cache.entity.Orders;

/**
 * @Author: w
 * @Date: 2021/2/19 22:29
 */
public interface CachePunctureService extends IService<Orders> {

    R getOrderDetail(Long id);
}
