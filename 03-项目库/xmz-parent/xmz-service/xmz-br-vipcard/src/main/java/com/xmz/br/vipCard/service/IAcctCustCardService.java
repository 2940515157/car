package com.xmz.br.vipCard.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctCustCard;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.MyAcctCustCard;
import com.xmz.br.vipCard.entity.vo.AcctCustCardQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户卡 服务类
 * </p>
 *
 * @author zt
 * @since 2021-06-04
 */
public interface IAcctCustCardService extends IService<AcctCustCard> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctCustCardQuery  查询条件对象
     * @return
     */
    Page<MyAcctCustCard> myPage(Page<MyAcctCustCard> pageParam, AcctCustCardQuery acctCustCardQuery);

    List<MyAcctCustCard> queryPhone(AcctCustCardQuery acctCustCardQuery);

    boolean add(Map<String,String> data);

    //查询可转赠优惠券的用户
    Page<MyAcctCustCard> myPage2(Page<MyAcctCustCard> pageParam,
                                  AcctCustCardQuery acctCustCardQuery);
}
