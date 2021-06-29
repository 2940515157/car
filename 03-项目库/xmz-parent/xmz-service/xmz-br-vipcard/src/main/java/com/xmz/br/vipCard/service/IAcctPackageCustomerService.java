package com.xmz.br.vipCard.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctPackageCustomer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.MyAcctPackageCustomer;
import com.xmz.br.vipCard.entity.vo.AcctPackageCustomerQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 客户已购买套餐 服务类
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
public interface IAcctPackageCustomerService extends IService<AcctPackageCustomer> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctPackageCustomerQuery  查询条件对象
     * @return
     */
    Page<MyAcctPackageCustomer> myPage(Page<MyAcctPackageCustomer> pageParam,
                                       AcctPackageCustomerQuery acctPackageCustomerQuery);

    boolean add(Map<String,String> data);

    AcctPackageCustomer queryDate(AcctPackageCustomerQuery acctPackageCustomerQuery);
}
