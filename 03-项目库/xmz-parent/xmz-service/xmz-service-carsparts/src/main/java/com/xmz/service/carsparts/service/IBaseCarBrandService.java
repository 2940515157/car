package com.xmz.service.carsparts.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.carsparts.entity.pojo.BaseCarBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 品牌(车辆) 服务类
 * </p>
 *
 * @author 江志威
 * @since 2021-06-15
 */
public interface IBaseCarBrandService extends IService<BaseCarBrand> {
    /**
     * 分页查询未选择品牌
     * @return
     */
    //Page<BaseCarBrand> queryQueryWei(Page<BaseCarBrand> pageParam);

    /**
     * 分页查询以选择品牌
     * @return
     */
    List<BaseCarBrand> queryQueryYi(String bcid);
}
