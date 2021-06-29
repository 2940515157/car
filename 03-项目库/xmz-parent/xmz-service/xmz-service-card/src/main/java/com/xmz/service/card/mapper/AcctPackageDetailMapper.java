package com.xmz.service.card.mapper;

import com.xmz.service.card.entity.pojo.AcctPackageDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.MyAcctPackageDetail;

import java.util.List;

/**
 * <p>
 * 套餐服务明细 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-18
 */
public interface AcctPackageDetailMapper extends BaseMapper<AcctPackageDetail> {

    /**
     * 查询
     * @return
     */
    List<MyAcctPackageDetail> getApUuid(String apUuid);
}
