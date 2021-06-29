package com.xmz.service.card.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctPackageArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.MyAcctPackageArea;

/**
 * <p>
 * 套餐使用范围 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-21
 */
public interface AcctPackageAreaMapper extends BaseMapper<AcctPackageArea> {

    //根据套餐产品的id 查询出卡与使用区域关系表
    Page<MyAcctPackageArea> queryAllPage(Page<MyAcctPackageArea> pageParam, String apUuid);

}
