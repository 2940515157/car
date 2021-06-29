package com.xmz.service.basedata.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.basedata.entity.pojo.BaseCarCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.basedata.entity.pojo.BaseCarCategoryAll;
import com.xmz.service.basedata.entity.vo.BaseCarCategoryQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 车系 Mapper 接口
 * </p>
 *
 * @author hyc
 * @since 2021-06-08
 */
public interface BaseCarCategoryMapper extends BaseMapper<BaseCarCategory> {
    Page<BaseCarCategoryAll> myPage(Page<BaseCarCategoryAll> pageParam,
                                    @Param(value = "bcc") BaseCarCategoryQuery baseCarCategoryQuery);
}
