package com.xmz.service.card.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCardSetup;
import com.xmz.service.card.entity.vo.AcctCardSetupQuery;
import com.xmz.service.card.entity.vo.MyAcctCardSetup;
import com.xmz.service.card.mapper.AcctCardSetupMapper;
import com.xmz.service.card.service.IAcctCardSetupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 储值卡产品定义 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-01
 */
@Service
public class AcctCardSetupServiceImpl extends ServiceImpl<AcctCardSetupMapper, AcctCardSetup> implements IAcctCardSetupService {

    @Autowired(required = false)
    AcctCardSetupMapper acctCardSetupMapper;

    /**
     * 储值卡产品定义 分页条件查询
     * @param pageParam 分页参数
     * @param acctCardSetupQuery 查询条件对象
     * @return
     */
    @Override
    public Page<MyAcctCardSetup> queryAllAcct(Page<MyAcctCardSetup> pageParam, AcctCardSetupQuery acctCardSetupQuery) {
        return acctCardSetupMapper.queryAllAcct(pageParam,acctCardSetupQuery);
    }


}
