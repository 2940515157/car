package com.xmz.service.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCountrys;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 国家信息 服务类
 * </p>
 *
 * @author leiyaping
 * @since 2021-06-03
 */
public interface IBaseCountrysService extends IService<BaseCountrys> {
    Page<BaseCountrys> queryPage(Page<BaseCountrys> page);

}
