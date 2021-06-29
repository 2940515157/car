package com.xmz.service.hr.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.HrEmployees;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.hr.entity.pojo.HrEmployeesAll;
import com.xmz.service.hr.entity.vo.HrEmployeesQuery;

import java.util.List;

/**
 * <p>
 * 员工信息 服务类
 * </p>
 *
 * @author hyc
 * @since 2021-05-24
 */
public interface IHrEmployeesService extends IService<HrEmployees> {

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param hrEmployeesQuery  查询条件对象
     * @return
     */
    Page<HrEmployeesAll> myPage(Page<HrEmployeesAll> pageParam,
                                HrEmployeesQuery hrEmployeesQuery);

    //设置离职状态
    int setStatus(HrEmployees hrEmployees);

    /**
     * 查询所有门店的领卡人
     * @param hrEmployees
     * @return
     */
    List<HrEmployees> queryName(HrEmployees hrEmployees);

}
