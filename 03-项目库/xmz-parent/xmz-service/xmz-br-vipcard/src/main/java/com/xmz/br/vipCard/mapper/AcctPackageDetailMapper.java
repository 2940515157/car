package com.xmz.br.vipCard.mapper;

import com.xmz.br.vipCard.entity.pojo.AcctPackageDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.vipCard.entity.pojo.MyAcctPackageDetail;

import java.util.List;

/**
 * <p>
 * 套餐服务明细 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-06-16
 */
public interface AcctPackageDetailMapper extends BaseMapper<AcctPackageDetail> {

    /**
     * 根据套餐id查询套餐服务明细
     * @param apUuid
     * @return
     */
    List<MyAcctPackageDetail> queryApUuid(String apUuid);
}
