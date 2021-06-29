package com.xmz.service.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCarBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.basedata.entity.vo.BaseCarQuery;


/**
 * <p>
 * 品牌(车辆) 服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-05-25
 */
public interface IBaseCarBrandService extends IService<BaseCarBrand> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param baseCarQuery  查询条件对象
     * @return
     */
    Page<BaseCarBrand> myPage(Page<BaseCarBrand> pageParam,
                              BaseCarQuery baseCarQuery);

}
