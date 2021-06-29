package com.xmz.br.vipCard.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.BrPackage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.vipCard.entity.pojo.MyAcctBrCard;
import com.xmz.br.vipCard.entity.pojo.MyBrPackage;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.BrPackageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 门店套餐 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
public interface BrPackageMapper extends BaseMapper<BrPackage> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param brPackageQuery  查询条件对象
     * @return
     */
    Page<MyBrPackage> myPage(Page<MyBrPackage> pageParam,
                             @Param(value = "bp") BrPackageQuery brPackageQuery);

    //查询所有门店已上架的套餐同时还是有效期内的套餐
    List<MyBrPackage> listQuery(@Param(value = "bp") BrPackageQuery brPackageQuery);



}
