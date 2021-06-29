package com.xmz.br.vipCard.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctCardSetup;
import com.xmz.br.vipCard.entity.pojo.MyAcctCardSetup;
import com.xmz.br.vipCard.entity.vo.AcctCardSetupQuery;
import com.xmz.br.vipCard.mapper.AcctCardSetupMapper;
import com.xmz.br.vipCard.service.IAcctCardSetupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 储值卡产品定义 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-06-09
 */
@Service
public class AcctCardSetupServiceImpl extends ServiceImpl<AcctCardSetupMapper, AcctCardSetup> implements IAcctCardSetupService {

    @Override
    public List<MyAcctCardSetup> listAcs(AcctCardSetupQuery acctCardSetupQuery) {
        return baseMapper.listAcs(acctCardSetupQuery);
    }

    @Override
    public Page<MyAcctCardSetup> myPage(Page<MyAcctCardSetup> pageParam, AcctCardSetupQuery acctCardSetupQuery) {
        return baseMapper.myPage(pageParam,acctCardSetupQuery);
    }
}
