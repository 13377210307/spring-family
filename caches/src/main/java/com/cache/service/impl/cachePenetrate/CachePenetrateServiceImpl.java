package com.cache.service.impl.cachePenetrate;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cache.entity.Orders;
import com.cache.mapper.OrderMapper;
import com.cache.service.cachePenetrate.CachePenetrateService;
import com.cache.vo.NullValueVo;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: w
 * @Date: 2021/2/19 15:11
 */
@Service
public class CachePenetrateServiceImpl extends ServiceImpl<OrderMapper, Orders> implements CachePenetrateService {

    // redis中的目录
    private static final String PREFIX = "orders:";

    // 布隆过滤器集合
    private static BloomFilter<Long> keysBloomFilters = null;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 使用缓存
     *
     * 1：请求数据先去缓存中查询
     * 2：缓存中存在直接返回，缓存不存在，请求数据库
     * 3：数据库中存在，更新缓存并返回数据，数据库中不存在，返回null
     *
     * 可能会存在缓存穿透问题
     *
     * 缓存穿透问题演示
     *
     * 概念：客户端发送大量请求访问一个缓存中不存在的key，导致请求不经过缓存直接到达数据库
     *
     * 解决方案：
     *
     * 1：缓存null值：此方案只能解决一个key访问多次的情况，对于
     *
     * 2：使用布隆过滤器
     *
     *
     * @param id
     * @return
     */
    @Override
    public R getOrderById(Long id) {
        // 访问redis
        Orders orders;
        orders = (Orders)this.redisTemplate.opsForValue().get(PREFIX + id);
        // 命中缓存
        if (orders != null) {
            // 缓存数据不为空，直接返回
            return R.ok(orders);
        }else {
            // 缓存数据为空，查询数据库
            orders = this.baseMapper.selectById(id);
            if (orders != null) {
                // 数据库中有该数据：1：设置进缓存  2：返回数据
                this.redisTemplate.opsForValue().set(PREFIX + id,orders,10L, TimeUnit.MINUTES);
            }
        }
        return R.ok(orders);
    }


    /**
     * 设置空值解决缓存穿透问题：只解决了一个key多次访问问题
     *
     * 1：在访问数据库时，若数据不存在，则依然将数据设置进缓存，但value值为null
     * 2：下次请求访问该key值的时候，判断该数据是否为空值数据，这样就不会查询数据库了
     *
     * 存在问题：
     * 1：只能对单个key生效，若换成其他不存在的key访问依然会出现此问题
     * 2：导致redis中存在多个null值，占用redis空间
     * @param id
     * @return
     */
    @Override
    public R solveBySetNull(Long id) {
        // 访问redis
        Orders orders;
        Object cacheOrders = this.redisTemplate.opsForValue().get(PREFIX + id);
        // 命中缓存
        if (cacheOrders != null) {
            if (cacheOrders instanceof NullValueVo) {
                // 空数据
                return R.failed("不存在该数据");
            }
            // 缓存数据不为空，直接返回
            return R.ok(cacheOrders);
        }else {
            // 缓存数据为空，查询数据库
            orders = this.baseMapper.selectById(id);
            if (orders != null) {
                // 数据库中有该数据：1：设置进缓存  2：返回数据
                this.redisTemplate.opsForValue().set(PREFIX + id,orders,10L, TimeUnit.MINUTES);
            }else {
                // 将value值设置为null更新缓存
                this.redisTemplate.opsForValue().set(PREFIX + id,new NullValueVo(),10L, TimeUnit.MINUTES);
                // 数据库中没有数据，返回null值
                return R.failed("不存在该数据");
            }
        }
        return R.ok(orders);
    }

    /**
     * 使用布隆过滤器解决
     *
     * 1：将数据库中的key值加入到布隆过滤器中
     * 2：客户端请求之后先经过布隆过滤器判断该key值是否存在：不存在直接返回无结果
     * 3：存在与正常情况一致
     *
     * 注意：初始化布隆过滤器我们可以
     * 1：使用一个定时器去定时查询数据库然后将数据插入布隆过滤器集合中
     * 2：或者使用一个mq在数据修改之后，去异步执行插入
     */
    @Override
    public R solveByBloomFilter(Long id) {
        Orders orders = null;
        // 先判断布隆过滤器是否存在
        if (keysBloomFilters == null) {
            // 集合不存在，进行初始化
            QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
            List<Orders> allOrders = this.baseMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(allOrders)) {
                List<Long> ids = allOrders.stream().map(Orders::getId).collect(Collectors.toList());
                initBloomFilter(ids);
            }else {
                return R.failed("数据库中无数据....");
            }
        }else {
            // 先进行布隆过滤器过滤
            if (!keysBloomFilters.mightContain(id)) {
                // 不存在
                return R.failed("不存在该数据");
            }else {
                System.out.println("未查询数据库....");
                // 查询缓存
                orders = (Orders)this.redisTemplate.opsForValue().get(PREFIX + id);
                // 命中缓存
                if (orders != null) {
                    // 缓存数据不为空，直接返回
                    return R.ok(orders);
                }else {
                    System.out.println("查询数据库...");
                    // 缓存数据为空，查询数据库
                    orders = this.baseMapper.selectById(id);
                    if (orders != null) {
                        // 数据库中有该数据：1：设置进缓存  2：返回数据
                        this.redisTemplate.opsForValue().set(PREFIX + id,orders,10L, TimeUnit.MINUTES);
                    }
                }
            }
        }
        return R.ok(orders);
    }

    // 初始化布隆过滤器
    private void initBloomFilter(List<Long> keys) {
        System.out.println("进入布隆过滤器初始化....");
        // 初始化布隆过滤器集合
        keysBloomFilters = BloomFilter.create(Funnels.longFunnel(),100000,0.01);

        // 将key值加入到集合中
        keys.forEach(key -> {
            keysBloomFilters.put(key);
        });
    }
}
