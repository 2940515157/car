package com.xmz.service.serviceproduct.entity.vo;

import com.xmz.service.serviceproduct.entity.pojo.BaseServCatalogPart;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装总部服务目录与配件目录的对应关系的返回结果
 */
@Data
public class MyBaseServCatalogPart extends BaseServCatalogPart implements Serializable {
        private String bptName; //配件名称

}
