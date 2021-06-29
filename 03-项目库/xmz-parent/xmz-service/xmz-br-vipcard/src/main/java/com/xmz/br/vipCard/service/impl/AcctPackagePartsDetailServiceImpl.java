package com.xmz.br.vipCard.service.impl;

import com.xmz.br.vipCard.entity.pojo.AcctPackagePartsDetail;
import com.xmz.br.vipCard.entity.pojo.MyAcctPackagePartsDetail;
import com.xmz.br.vipCard.mapper.AcctPackagePartsDetailMapper;
import com.xmz.br.vipCard.service.IAcctPackagePartsDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 套餐配件明细 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-06-21
 */
@Service
public class AcctPackagePartsDetailServiceImpl extends ServiceImpl<AcctPackagePartsDetailMapper, AcctPackagePartsDetail> implements IAcctPackagePartsDetailService {

    @Override
    public List<MyAcctPackagePartsDetail> list(String apUuid) {
        return baseMapper.list(apUuid);
    }
}
