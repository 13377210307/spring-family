package com.cache.controller.penetrate;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.cache.service.cachePenetrate.CachePenetrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: w
 * @Date: 2021/2/19 15:03
 *
 * 缓存穿透问题
 */
@RestController
@RequestMapping("/cachePenetrate")
public class CachePenetrateController extends ApiController {

    @Autowired
    private CachePenetrateService orderService;


    @GetMapping("/{id}")
    public R getOrderById(@PathVariable("id") Long id) {
        return this.orderService.getOrderById(id);
    }

    /**
     * 设置空值解决缓存穿透问题
     */
    @GetMapping("/solveBySetNull/{id}")
    public R solveBySetNull(@PathVariable("id") Long id) {
        return this.orderService.solveBySetNull(id);
    }

    /**
     * 使用布隆过滤器解决缓存穿透问题
     * @param id
     * @return
     */
    @GetMapping("/solveByBloomFilter/{id}")
    public R solveByBloomFilter(@PathVariable("id") Long id) {
        return this.orderService.solveByBloomFilter(id);
    }
}
