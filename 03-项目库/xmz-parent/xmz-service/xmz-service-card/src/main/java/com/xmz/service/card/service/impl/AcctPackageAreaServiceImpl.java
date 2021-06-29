package com.xmz.service.card.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctPackageArea;
import com.xmz.service.card.entity.vo.MyAcctPackageArea;
import com.xmz.service.card.mapper.AcctPackageAreaMapper;
import com.xmz.service.card.service.IAcctPackageAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 套餐使用范围 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-21
 */
@Service
public class AcctPackageAreaServiceImpl extends ServiceImpl<AcctPackageAreaMapper, AcctPackageArea> implements IAcctPackageAreaService {

    @Override
    public Page<MyAcctPackageArea> queryAllPage(Page<MyAcctPackageArea> pageParam, String apUuid) {
        return baseMapper.queryAllPage(pageParam,apUuid);
    }
}
