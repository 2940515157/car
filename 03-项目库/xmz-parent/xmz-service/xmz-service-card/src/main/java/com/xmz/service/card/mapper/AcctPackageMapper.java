package com.xmz.service.card.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctPackage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.MyAcctPackage;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 套餐 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-03
 */
public interface AcctPackageMapper extends BaseMapper<AcctPackage> {

    /**
     * 套餐产品分页条件查询
     * @param pageParam   分页参数
     * @param acctPackage 查询条件对象
     * @return
     */
    Page<MyAcctPackage> queryAllAcctPackage(Page<MyAcctPackage> pageParam,@Param("acpa") AcctPackage acctPackage);

}
