package com.xmz.service.basedata.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCarBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.basedata.entity.vo.BaseCarQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 品牌(车辆) Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-05-25
 */
public interface BaseCarBrandMapper extends BaseMapper<BaseCarBrand> {

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param baseCarQuery  查询条件对象
     * @return
     */
    Page<BaseCarBrand> myPage(Page<BaseCarBrand> pageParam,
                           @Param(value = "bcq") BaseCarQuery baseCarQuery);

}
