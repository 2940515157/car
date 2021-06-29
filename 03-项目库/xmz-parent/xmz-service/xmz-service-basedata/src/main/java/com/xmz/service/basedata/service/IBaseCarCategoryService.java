package com.xmz.service.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCarCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.basedata.entity.pojo.BaseCarCategoryAll;
import com.xmz.service.basedata.entity.vo.BaseCarCategoryQuery;

/**
 * <p>
 * 车系 服务类
 * </p>
 *
 * @author hyc
 * @since 2021-06-08
 */
public interface IBaseCarCategoryService extends IService<BaseCarCategory> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param BaseCarCategoryQuery  查询条件对象
     * @return
     */
    Page<BaseCarCategoryAll> myPage(Page<BaseCarCategoryAll> pageParam,
                                    BaseCarCategoryQuery BaseCarCategoryQuery);

}
