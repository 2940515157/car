package com.xmz.br.vipCard.service;

import com.xmz.br.vipCard.entity.pojo.AcctPackagePartsDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.MyAcctPackagePartsDetail;

import java.util.List;

/**
 * <p>
 * 套餐配件明细 服务类
 * </p>
 *
 * @author zt
 * @since 2021-06-21
 */
public interface IAcctPackagePartsDetailService extends IService<AcctPackagePartsDetail> {
    List<MyAcctPackagePartsDetail> list(String apUuid);
}
