package com.xmz.service.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCountrys;
import com.xmz.service.basedata.entity.pojo.BaseProvinces;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 省份信息 服务类
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-03
 */
public interface IBaseProvincesService extends IService<BaseProvinces> {
    Page<BaseProvinces> queryPage(Page<BaseProvinces> page,String countryUuid);
    BaseProvinces queryById(String countryUuid,String provUuid);

}
