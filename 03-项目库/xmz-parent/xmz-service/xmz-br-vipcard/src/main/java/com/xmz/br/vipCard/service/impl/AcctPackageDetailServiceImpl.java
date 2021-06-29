package com.xmz.br.vipCard.service.impl;

import com.xmz.br.vipCard.entity.pojo.AcctPackageDetail;
import com.xmz.br.vipCard.entity.pojo.MyAcctPackageDetail;
import com.xmz.br.vipCard.mapper.AcctPackageDetailMapper;
import com.xmz.br.vipCard.service.IAcctPackageDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 套餐服务明细 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-06-16
 */
@Service
public class AcctPackageDetailServiceImpl extends ServiceImpl<AcctPackageDetailMapper, AcctPackageDetail> implements IAcctPackageDetailService {

    @Override
    public List<MyAcctPackageDetail> queryApUuid(String apUuid) {
        return baseMapper.queryApUuid(apUuid);
    }
}
