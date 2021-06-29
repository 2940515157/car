package com.xmz.br.vipCard.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctPackage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.vo.AcctPackageQuery;

import java.util.List;

/**
 * <p>
 * 套餐 服务类
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
public interface IAcctPackageService extends IService<AcctPackage> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @return
     */
    Page<AcctPackage> myPage(Page<AcctPackage> pageParam, AcctPackageQuery acctPackageQuery);

    //根据套餐名称查询套餐
    AcctPackage queryName(String name);
}
