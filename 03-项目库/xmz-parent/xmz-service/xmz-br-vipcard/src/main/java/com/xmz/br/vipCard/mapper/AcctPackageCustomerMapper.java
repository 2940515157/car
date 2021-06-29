package com.xmz.br.vipCard.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctPackageCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.vipCard.entity.pojo.MyAcctCustCard;
import com.xmz.br.vipCard.entity.pojo.MyAcctPackageCustomer;
import com.xmz.br.vipCard.entity.vo.AcctCustCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctPackageCustomerQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>
 * 客户已购买套餐 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
public interface AcctPackageCustomerMapper extends BaseMapper<AcctPackageCustomer> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctPackageCustomerQuery  查询条件对象
     * @return
     */
    Page<MyAcctPackageCustomer> myPage(Page<MyAcctPackageCustomer> pageParam,
                                       @Param(value = "apc") AcctPackageCustomerQuery acctPackageCustomerQuery);

    AcctPackageCustomer queryDate(@Param(value = "apc") AcctPackageCustomerQuery acctPackageCustomerQuery);
}
