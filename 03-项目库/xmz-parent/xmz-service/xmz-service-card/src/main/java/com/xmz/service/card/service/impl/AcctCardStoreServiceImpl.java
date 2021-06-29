package com.xmz.service.card.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCardStore;
import com.xmz.service.card.entity.vo.AcctCardStoredQuery;
import com.xmz.service.card.entity.vo.MyAcctCardStored;
import com.xmz.service.card.mapper.AcctCardStoreMapper;
import com.xmz.service.card.service.IAcctCardStoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 卡库 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-09
 */
@Service
public class AcctCardStoreServiceImpl extends ServiceImpl<AcctCardStoreMapper, AcctCardStore> implements IAcctCardStoreService {
    @Autowired(required = false)
    AcctCardStoreMapper acctCardStoreMapper;
    /**
     * 分页提条件查询制卡管理
     * @param pageParam
     * @param acctCardStoredQuery
     * @return
     */
    @Override
    public Page<MyAcctCardStored> queryAllZhiKa(Page<MyAcctCardStored> pageParam, AcctCardStoredQuery acctCardStoredQuery) {
        return acctCardStoreMapper.queryAllZhiKa(pageParam,acctCardStoredQuery);
    }

    /**
     * 根据编号查询 减去  CARD_STORE_UUID
     * @param acctCardStore
     * @return
     */
    @Override
    public boolean updateNum(AcctCardStore acctCardStore) {
        return acctCardStoreMapper.updateNum(acctCardStore);
    }


}
