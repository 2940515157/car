package com.xmz.service.card.entity.vo;

import com.xmz.service.card.entity.pojo.AcctBrCard;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装发卡管理的返回结果
 */
@Data
public class MyAcctBrCard extends AcctBrCard implements Serializable {

        private  String brName;  //门店名称
        private  String empName;    //领卡人
        private  String kaShang; //卡商
        private  String stutas;  //状态


}
