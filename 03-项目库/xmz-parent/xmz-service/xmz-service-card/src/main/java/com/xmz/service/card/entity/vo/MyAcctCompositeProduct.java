package com.xmz.service.card.entity.vo;

import com.xmz.service.card.entity.pojo.AcctCompositeProduct;
import lombok.Data;

import java.io.Serializable;

/**
 * 营销组合
 */
@Data
public class MyAcctCompositeProduct extends AcctCompositeProduct implements Serializable {

            private  String statusName ; //状态
            private  String bizTypeName; //业务类型
            private  String serviceTypeName;//服务类型

}
