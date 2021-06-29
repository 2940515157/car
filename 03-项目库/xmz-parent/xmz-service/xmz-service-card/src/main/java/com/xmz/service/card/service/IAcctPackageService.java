package com.xmz.service.card.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctPackage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.vo.MyAcctPackage;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 套餐 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-03
 */
public interface IAcctPackageService extends IService<AcctPackage> {

    /**
     * 套餐产品分页条件查询
     * @param pageParam 分页参数
     * @param acctPackage 查询条件对象
     * @return
     */
    Page<MyAcctPackage> queryAllAcctPackage(Page<MyAcctPackage> pageParam, AcctPackage acctPackage);


    /**
     * 添加套餐
     * @return
     */
    boolean add(Map<String,String> data);
}
