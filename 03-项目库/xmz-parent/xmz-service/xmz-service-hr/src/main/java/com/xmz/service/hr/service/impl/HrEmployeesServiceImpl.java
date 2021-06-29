package com.xmz.service.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.HrEmployees;
import com.xmz.service.hr.entity.pojo.HrEmployeesAll;
import com.xmz.service.hr.entity.vo.HrEmployeesQuery;
import com.xmz.service.hr.mapper.HrEmployeesMapper;
import com.xmz.service.hr.service.IHrEmployeesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author hyc
 * @since 2021-05-24
 */
@Service
public class HrEmployeesServiceImpl extends ServiceImpl<HrEmployeesMapper, HrEmployees> implements IHrEmployeesService
{
    @Autowired(required = false)
    HrEmployeesMapper mapper;

    @Override
    public Page<HrEmployeesAll> myPage(Page<HrEmployeesAll> pageParam, HrEmployeesQuery hrEmployeesQuery) {
        return baseMapper.myPage(pageParam,hrEmployeesQuery);
    }

    @Override
    public int setStatus(HrEmployees hrEmployees) {
        return mapper.setStatus(hrEmployees);
    }

    /**
     * 查询所有门店的领卡人
     * @param hrEmployees
     * @return
     */
    @Override
    public List<HrEmployees> queryName(HrEmployees hrEmployees) {
        return mapper.queryName(hrEmployees);
    }
}
