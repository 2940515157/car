package com.xmz.service.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCarFactory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.basedata.entity.vo.BaseFactoryQuery;

import java.util.List;

/**
 * <p>
 * 厂家 服务类
 * </p>
 *
 * @author lm
 * @since 2021-05-31
 */
public interface IBaseCarFactoryService extends IService<BaseCarFactory> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param baseFactoryQuery  查询条件对象
     * @return
     */
    Page<BaseCarFactory> myPage(Page<BaseCarFactory> pageParam,
                              BaseFactoryQuery baseFactoryQuery);

    List<BaseCarFactory> queryByBrandUuid(String id);
}
