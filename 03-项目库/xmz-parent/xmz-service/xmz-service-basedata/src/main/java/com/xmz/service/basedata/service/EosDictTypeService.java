package com.xmz.service.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.basedata.entity.pojo.EosDictType;
import com.xmz.service.basedata.entity.pojo.QueryParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weikang
 * @since 2021-05-24
 */
public interface EosDictTypeService extends IService<EosDictType> {
    Page<EosDictType> queryPage(QueryParam queryParam, Page<EosDictType> page);
}
