package com.xmz.service.card.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCardStore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.vo.AcctCardStoredQuery;
import com.xmz.service.card.entity.vo.MyAcctCardStored;


/**
 * <p>
 * 卡库 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-09
 */
public interface IAcctCardStoreService extends IService<AcctCardStore> {
    /**
     * 分页提条件查询制卡管理
     * @param pageParam
     * @param acctCardStoredQuery
     * @return
     */
    Page<MyAcctCardStored> queryAllZhiKa(Page<MyAcctCardStored> pageParam,  AcctCardStoredQuery acctCardStoredQuery);

    //根据编号查询 减去  CARD_STORE_UUID
    boolean updateNum(AcctCardStore acctCardStore);
}
