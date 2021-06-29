package com.xmz.service.serviceproduct.entity.vo;

import lombok.Data;

/**
 * 用于封装点检目录的查询条件
 */
@Data
public class ServCheckupCatalogQuery {
    //项目名称
    private String catalogName;
    //点检目录
    private String directoryName;
}
