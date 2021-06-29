package com.xmz.service.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.basedata.entity.pojo.BaseCarType;
import com.xmz.service.basedata.entity.vo.CarTypeQueryParam;

import java.util.List;

/**
 * <p>
 * 车型 服务类
 * </p>
 *
 * @author weikang
 * @since 2021-06-02
 */
public interface IBaseCarTypeService extends IService<BaseCarType> {
    Page<BaseCarType> queryPage(Page<BaseCarType> page, CarTypeQueryParam queryParam);

    List<BaseCarType> queryList(CarTypeQueryParam queryParam);

    List<BaseCarType> list2();
    List<BaseCarType> list3();
    List<BaseCarType> list4();
    List<BaseCarType> list5();
    List<BaseCarType> list6();
}
