package com.xmz.service.card.mapper;

import com.xmz.service.card.entity.pojo.AcctPackagePartsDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.MyAcctPackagePartsDetail;

import java.util.List;

/**
 * <p>
 * 套餐配件明细 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-18
 */
public interface AcctPackagePartsDetailMapper extends BaseMapper<AcctPackagePartsDetail> {
    /**
     * 查询
     * @return
     */
    List<MyAcctPackagePartsDetail> getApUuid(String apUuid);
}
