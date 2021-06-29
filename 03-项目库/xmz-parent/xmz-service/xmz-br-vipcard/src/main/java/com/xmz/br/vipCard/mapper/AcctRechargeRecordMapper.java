package com.xmz.br.vipCard.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctRechargeRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.vipCard.entity.pojo.MyAcctBrCard;
import com.xmz.br.vipCard.entity.pojo.MyAcctRechargeRecord;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctRechargeRecordQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 充值记录表 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-06-09
 */
public interface AcctRechargeRecordMapper extends BaseMapper<AcctRechargeRecord> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctRechargeRecordQuery  查询条件对象
     * @return
     */
    Page<MyAcctRechargeRecord> myPage(Page<MyAcctRechargeRecord> pageParam,
                                      @Param(value = "arr") AcctRechargeRecordQuery acctRechargeRecordQuery);



}
