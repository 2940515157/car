package com.xmz.service.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCitys;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.basedata.entity.pojo.BaseProvinces;

/**
 * <p>
 * 城市 服务类
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-03
 */
public interface IBaseCitysService extends IService<BaseCitys> {
    Page<BaseCitys> queryPage(Page<BaseCitys> page,String countryUuid,String provUuid);
    BaseCitys queryById(String countryUuid,String provUuid,String cityUuid);
}
