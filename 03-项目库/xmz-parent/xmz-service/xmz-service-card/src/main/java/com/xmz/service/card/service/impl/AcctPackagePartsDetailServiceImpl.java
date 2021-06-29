package com.xmz.service.card.service.impl;

import com.xmz.service.card.entity.pojo.AcctPackagePartsDetail;
import com.xmz.service.card.entity.vo.MyAcctPackagePartsDetail;
import com.xmz.service.card.mapper.AcctPackagePartsDetailMapper;
import com.xmz.service.card.service.IAcctPackagePartsDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 套餐配件明细 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-18
 */
@Service
public class AcctPackagePartsDetailServiceImpl extends ServiceImpl<AcctPackagePartsDetailMapper, AcctPackagePartsDetail> implements IAcctPackagePartsDetailService {

    @Override
    public List<MyAcctPackagePartsDetail> getApUuid(String apUuid) {
        return baseMapper.getApUuid(apUuid);
    }
}
