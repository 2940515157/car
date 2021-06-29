package com.xmz.service.card.entity.vo;

import com.xmz.service.card.entity.pojo.AcctPackage;
import lombok.Data;

import java.io.Serializable;

/**
 * 用于封装套餐产品的查询结果
 */
@Data
public class MyAcctPackage extends AcctPackage implements Serializable {

        private String apTypeName;      //套餐类型
        private String statusName;      //状态
        private String origionTypeName; //发起类型
        private String bizTypeName;     //业务分类
        private String serviceTypeName; //服务类型

}
