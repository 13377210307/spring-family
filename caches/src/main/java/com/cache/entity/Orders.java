package com.cache.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: w
 * @Date: 2021/2/19 15:12
 */
@Data
public class Orders implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
}
