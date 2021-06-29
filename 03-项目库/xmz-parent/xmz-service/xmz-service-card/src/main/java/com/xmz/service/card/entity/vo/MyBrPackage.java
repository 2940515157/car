package com.xmz.service.card.entity.vo;


import com.xmz.service.card.entity.pojo.BrPackage;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用于封装门店套餐的查询结果
 */
@Data
public class MyBrPackage extends BrPackage implements Serializable {

    private String brCode;          //门店编号
    private String brName;          //门店名称
    private String apName;          //套餐名称
    private String apType;          //套餐类型
    private BigDecimal totalPrice;  //套餐售价
    private String apmUuid;         //套餐模板
    private String serviceType;     //服务类型


    private String dictname1;    //类型
    private String dictname2;    //类型
    private String dictname3;    //类型
    private String dictname4;    //类型

    private BigDecimal saleRate;    //销售套餐提成比率
    private BigDecimal discountRate;//折扣率
    private String canOwner;        //是否自动专属
    private BigDecimal validMonth;  //使用有效期（月）
    private Date startDate;         //有效期(开始)
    private Date endDate;           //有效期(结束)
    private String bizType;         //业务分类

}
