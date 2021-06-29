package com.xmz.service.card.service.impl;

import com.xmz.service.card.entity.pojo.AcctPackageDetail;
import com.xmz.service.card.entity.vo.MyAcctPackageDetail;
import com.xmz.service.card.mapper.AcctPackageDetailMapper;
import com.xmz.service.card.service.IAcctPackageDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 套餐服务明细 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-18
 */
@Service
public class AcctPackageDetailServiceImpl extends ServiceImpl<AcctPackageDetailMapper, AcctPackageDetail> implements IAcctPackageDetailService {

    @Override
    public List<MyAcctPackageDetail> getApUuid(String apUuid) {
        return baseMapper.getApUuid(apUuid);
    }
}
