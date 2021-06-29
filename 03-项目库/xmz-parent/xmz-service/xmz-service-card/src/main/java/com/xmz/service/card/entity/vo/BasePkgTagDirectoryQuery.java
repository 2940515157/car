package com.xmz.service.card.entity.vo;

import lombok.Data;

/**
 * 用与封装查询套餐标签的查询条件
 */
@Data
public class BasePkgTagDirectoryQuery {

    private String bptdUuid;//主id
    private String label;   //标签目录
}
