package com.cache.service.bitMap;

import com.cache.vo.LikeVo;

/**
 * @Author: w
 * @Date: 2021/2/20 9:43
 */
public interface BitMapService {

    Boolean likeOrUnlike(LikeVo likeVo);

    Boolean isLike(Long contentId, Long userId);

    Long likeCount(Long contentId);

}
