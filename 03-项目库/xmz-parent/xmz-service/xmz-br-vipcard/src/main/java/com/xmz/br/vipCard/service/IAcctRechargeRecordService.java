package com.xmz.br.vipCard.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctRechargeRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.MyAcctRechargeRecord;
import com.xmz.br.vipCard.entity.vo.AcctRechargeRecordQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 充值记录表 服务类
 * </p>
 *
 * @author zt
 * @since 2021-06-09
 */
public interface IAcctRechargeRecordService extends IService<AcctRechargeRecord> {

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctRechargeRecordQuery  查询条件对象
     * @return
     */
    Page<MyAcctRechargeRecord> myPage(Page<MyAcctRechargeRecord> pageParam, AcctRechargeRecordQuery acctRechargeRecordQuery);

    boolean add( MyAcctRechargeRecord myAcctRechargeRecord);
}
