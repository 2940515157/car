package com.xmz.service.basedata.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCarCategory;
import com.xmz.service.basedata.entity.pojo.BaseCarCategoryAll;
import com.xmz.service.basedata.entity.vo.BaseCarCategoryQuery;
import com.xmz.service.basedata.mapper.BaseCarCategoryMapper;
import com.xmz.service.basedata.service.IBaseCarCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车系 服务实现类
 * </p>
 *
 * @author hyc
 * @since 2021-06-08
 */
@Service
public class BaseCarCategoryServiceImpl extends ServiceImpl<BaseCarCategoryMapper, BaseCarCategory> implements IBaseCarCategoryService {

    @Override
    public Page<BaseCarCategoryAll> myPage(Page<BaseCarCategoryAll> pageParam, BaseCarCategoryQuery baseCarCategoryQuery) {
        return baseMapper.myPage(pageParam,baseCarCategoryQuery);
    }
}
