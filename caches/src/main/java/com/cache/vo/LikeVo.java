package com.cache.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: w
 * @Date: 2021/2/20 9:47
 *
 * 点赞需要参数
 */
@Data
public class LikeVo implements Serializable {

    // 操作朋友圈
    private Long contentId;

    // 操作用户id
    private Long userId;

    // 操作类型
    private Boolean type;
}
