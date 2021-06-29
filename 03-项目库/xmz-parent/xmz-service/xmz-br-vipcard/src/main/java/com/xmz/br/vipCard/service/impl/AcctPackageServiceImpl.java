package com.xmz.br.vipCard.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctPackage;
import com.xmz.br.vipCard.entity.vo.AcctPackageQuery;
import com.xmz.br.vipCard.mapper.AcctPackageMapper;
import com.xmz.br.vipCard.service.IAcctPackageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 套餐 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
@Service
public class AcctPackageServiceImpl extends ServiceImpl<AcctPackageMapper, AcctPackage> implements IAcctPackageService {

    @Override
    public Page<AcctPackage> myPage(Page<AcctPackage> pageParam, AcctPackageQuery acctPackageQuery) {
        return baseMapper.myPage(pageParam,acctPackageQuery);
    }

    @Override
    public AcctPackage queryName(String name) {
        return baseMapper.queryName(name);
    }
}
