package com.xmz.service.card.service;

import com.xmz.service.card.entity.pojo.AcctPackageDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.vo.MyAcctPackageDetail;

import java.util.List;

/**
 * <p>
 * 套餐服务明细 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-18
 */
public interface IAcctPackageDetailService extends IService<AcctPackageDetail> {
    /**
     * 查询
     * @return
     */
    List<MyAcctPackageDetail> getApUuid(String apUuid);
}
