package com.cache.service.impl.bitMap;

import com.cache.service.bitMap.BitMapService;
import com.cache.vo.LikeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: w
 * @Date: 2021/2/20 9:44
 */
@Service
public class BitMapServiceImpl implements BitMapService {

    private static final String PREFIX = "bitmap:";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 朋友圈点赞或取消点赞
     * @param likeVo
     * @return
     */
    @Override
    public Boolean likeOrUnlike(LikeVo likeVo) {
        return this.redisTemplate.opsForValue().setBit(PREFIX + likeVo.getContentId(),likeVo.getUserId(),likeVo.getType());
    }

    /**
     * 查看是否点赞
     */
    @Override
    public Boolean isLike(Long contentId, Long userId) {
        return this.redisTemplate.opsForValue().getBit(PREFIX +contentId, userId);
    }

    /**
     * 查看点赞次数
     */
    @Override
    public Long likeCount(Long contentId) {
        return (long)stringRedisTemplate.execute((RedisCallback<Long>) con -> con.bitCount((PREFIX + contentId).getBytes()));
    }
}
