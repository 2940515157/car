package com.xmz.service.hr.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.HrOrg;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.hr.entity.vo.HrOrgQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门信息 服务类
 * </p>
 *
 * @author leiyaping
 * @since 2021-05-28
 */
public interface IHrOrgService extends IService<HrOrg> {
    /**
     * 多条件分页查询
     */
    Page<HrOrg> pageQuery(Page<HrOrg> pagePram, HrOrgQuery hrOrgQuery);

    //分页查询全部
    Page<HrOrg> queryAllPage(Page<HrOrg> pageParam);

    //查询所有营运中心的部门
    List<HrOrg> queryAllYingYun(String orgUuid);
}
