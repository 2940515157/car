package com.xmz.br.vipCard.mapper;

import com.xmz.br.vipCard.entity.pojo.AcctPackagePartsDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.vipCard.entity.pojo.MyAcctPackagePartsDetail;

import java.util.List;

/**
 * <p>
 * 套餐配件明细 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-06-21
 */
public interface AcctPackagePartsDetailMapper extends BaseMapper<AcctPackagePartsDetail> {

    List<MyAcctPackagePartsDetail> list(String apUuid);
}
