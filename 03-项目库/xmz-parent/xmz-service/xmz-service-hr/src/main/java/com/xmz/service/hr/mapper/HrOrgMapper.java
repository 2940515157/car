package com.xmz.service.hr.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.HrOrg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 部门信息 Mapper 接口
 * </p>
 *
 * @author leiyaping
 * @since 2021-05-28
 */
public interface HrOrgMapper extends BaseMapper<HrOrg> {

        //分页查询全部
        Page<HrOrg> queryAllPage(Page<HrOrg> pageParam);

        //查询所有营运中心的部门
        List<HrOrg> queryAllYingYun(String orgUuid);
}
