package com.cache.service.impl.cachePuncture;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cache.entity.Orders;
import com.cache.mapper.OrderMapper;
import com.cache.service.cachePuncture.CachePunctureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: w
 * @Date: 2021/2/19 22:30
 *
 * 对于缓存击穿问题
 * 1：可以使用分布式锁进行解决
 * 2：设置热点数据永不过期
 * 3：创建定时任务维护热点数据过期时间
 */
@Service
public class CachePunctureServiceImpl extends ServiceImpl<OrderMapper, Orders> implements CachePunctureService {

    // redis中的目录
    private static final String PREFIX = "orders:";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 缓存击穿演示：并发访问一个过期的热点数据
     */
    @Override
    public R getOrderDetail(Long id) {
        // 通过key值查询缓存中是否存在数据
        Object cacheOrder = this.redisTemplate.opsForValue().get(id);

        // 缓存中存在数据，进行返回
        if (cacheOrder != null) {
            return R.ok((Orders)cacheOrder);
        }else {
            // 查询数据库
            Orders orders = this.baseMapper.selectById(id);
            if (orders != null) {
                System.out.println("查询数据库...");
                // 更新缓存
                this.redisTemplate.opsForValue().set(PREFIX + id,orders,10L, TimeUnit.MINUTES);
                // 返回数据
                return R.ok(orders);
            }else {
                return R.failed("查询数据不存在...");
            }
        }
    }
}
