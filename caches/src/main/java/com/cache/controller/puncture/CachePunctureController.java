package com.cache.controller.puncture;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.cache.service.cachePuncture.CachePunctureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: w
 * @Date: 2021/2/19 22:33
 *
 * 缓存击穿问题：并发访问热点数据
 */
@RestController
@RequestMapping("/puncture")
public class CachePunctureController extends ApiController {

    @Autowired
    private CachePunctureService cachePunctureService;

    @GetMapping("/{id}")
    public R getOrderDetail(@PathVariable("id") Long id) {
       return this.cachePunctureService.getOrderDetail(id);
    }
}
