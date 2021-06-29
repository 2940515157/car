package com.xmz.br.vipCard.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyCustCustInfo extends CustCustInfo implements Serializable {
    private String licensePlate;//车牌号
    private String brName;//专属门店
    private String saname;//专属sa
    private String brand;//品牌
    private String carModel;//车型
    private String saleName;//销售名称
    private String carYear;//年款
    private String custLevel;//客户等级
    private String carUuid;//车辆id
    private String cardNo;//卡号
}
