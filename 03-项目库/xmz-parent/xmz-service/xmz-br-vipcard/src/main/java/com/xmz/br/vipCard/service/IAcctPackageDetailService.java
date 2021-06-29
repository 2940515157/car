package com.xmz.br.vipCard.service;

import com.xmz.br.vipCard.entity.pojo.AcctPackageDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.MyAcctPackageDetail;

import java.util.List;

/**
 * <p>
 * 套餐服务明细 服务类
 * </p>
 *
 * @author zt
 * @since 2021-06-16
 */
public interface IAcctPackageDetailService extends IService<AcctPackageDetail> {
    /**
     * 根据套餐id查询套餐服务明细
     * @param apUuid
     * @return
     */
    List<MyAcctPackageDetail> queryApUuid(String apUuid);
}
