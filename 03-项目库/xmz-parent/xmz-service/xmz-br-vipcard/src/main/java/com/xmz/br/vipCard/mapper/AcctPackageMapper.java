package com.xmz.br.vipCard.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctPackage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.vipCard.entity.pojo.MyAcctBrCard;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctPackageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 套餐 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
public interface AcctPackageMapper extends BaseMapper<AcctPackage> {

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @return
     */
    Page<AcctPackage> myPage(Page<AcctPackage> pageParam,
                             @Param(value = "ap") AcctPackageQuery acctPackageQuery);

    //根据套餐名称查询套餐
    AcctPackage queryName(String name);
}
