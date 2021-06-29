package com.xmz.service.card.service;

import com.xmz.service.card.entity.pojo.AcctPackagePartsDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.vo.MyAcctPackagePartsDetail;

import java.util.List;

/**
 * <p>
 * 套餐配件明细 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-18
 */
public interface IAcctPackagePartsDetailService extends IService<AcctPackagePartsDetail> {
    /**
     * 查询
     * @return
     */
    List<MyAcctPackagePartsDetail> getApUuid(String apUuid);
}
