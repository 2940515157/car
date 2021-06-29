package com.xmz.br.vipCard.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctCustCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.vipCard.entity.pojo.MyAcctBrCard;
import com.xmz.br.vipCard.entity.pojo.MyAcctCustCard;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctCustCardQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户卡 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-06-04
 */
public interface AcctCustCardMapper extends BaseMapper<AcctCustCard> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctCustCardQuery  查询条件对象
     * @return
     */
    Page<MyAcctCustCard> myPage(Page<MyAcctCustCard> pageParam,
                                @Param(value = "acc") AcctCustCardQuery acctCustCardQuery);

    //查询可充值卡的用户
    List<MyAcctCustCard> queryPhone(@Param(value = "acc") AcctCustCardQuery acctCustCardQuery);

    //查询可转赠优惠券的用户
    Page<MyAcctCustCard> myPage2(Page<MyAcctCustCard> pageParam,
                                @Param(value = "acc") AcctCustCardQuery acctCustCardQuery);
}
