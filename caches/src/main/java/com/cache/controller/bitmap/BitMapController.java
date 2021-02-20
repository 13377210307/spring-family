package com.cache.controller.bitmap;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.cache.service.bitMap.BitMapService;
import com.cache.vo.LikeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: w
 * @Date: 2021/2/20 9:40
 *
 * 位图应用场景
 * 微信朋友圈点赞功能
 * 1：点赞
 * 2：取消点赞
 * 3：查看是否点赞
 * 4：一共有多少点赞
 */

@RestController
@RequestMapping("/bitMap")
public class BitMapController extends ApiController {

    @Autowired
    private BitMapService bitMapService;

    // 点赞或取消点赞
    @PutMapping("/likeOrUnLike")
    public R likeOrUnlike(@RequestBody LikeVo likeVo) {
        return success(this.bitMapService.likeOrUnlike(likeVo));
    }

    // 查看是否点赞
    @GetMapping("/isLike/{contentId}/{userId}")
    public R isLike(@PathVariable("contentId") Long contentId,@PathVariable("userId") Long userId) {
        return success(this.bitMapService.isLike(contentId,userId));
    }

    // 查看点赞次数
    @GetMapping("/likeCount/{contentId}")
    public R likeCount(@PathVariable("contentId") Long contentId) {
        return success(this.bitMapService.likeCount(contentId));
    }
}
