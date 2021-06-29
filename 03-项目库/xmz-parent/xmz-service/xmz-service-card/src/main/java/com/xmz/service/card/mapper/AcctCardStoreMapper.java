package com.xmz.service.card.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCardStore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.AcctCardStoredQuery;
import com.xmz.service.card.entity.vo.MyAcctCardStored;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 卡库 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-09
 */
public interface AcctCardStoreMapper extends BaseMapper<AcctCardStore> {

    /**
     * 分页提条件查询制卡管理
     * @param pageParam
     * @param acctCardStoredQuery
     * @return
     */
    Page<MyAcctCardStored> queryAllZhiKa(Page<MyAcctCardStored> pageParam,@Param("acctS") AcctCardStoredQuery acctCardStoredQuery);


    //根据编号查询 减去  CARD_STORE_UUID
    boolean updateNum(AcctCardStore acctCardStore);
}
