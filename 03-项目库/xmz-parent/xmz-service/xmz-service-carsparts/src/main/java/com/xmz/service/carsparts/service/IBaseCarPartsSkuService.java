package com.xmz.service.carsparts.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.carsparts.entity.pojo.BaseCarPartsSku;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.carsparts.entity.vo.BaseCarPartsSkuQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 配件产品库(含品牌规格型号)(sku级) 服务类
 * </p>
 *
 * @author 江志威
 * @since 2021-06-16
 */
public interface IBaseCarPartsSkuService extends IService<BaseCarPartsSku> {
    /**
     * 分页查询
     * @return
     */
    Page<BaseCarPartsSku> queryQuery(Page<BaseCarPartsSku> pageParam, BaseCarPartsSkuQuery baseCarPartsSkuQuery);
}
