package com.xmz.service.hr.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.HrEmployees;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.hr.entity.pojo.HrEmployeesAll;
import com.xmz.service.hr.entity.vo.HrEmployeesQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 员工信息 Mapper 接口
 * </p>
 *
 * @author hyc
 * @since 2021-05-24
 */
public interface HrEmployeesMapper extends BaseMapper<HrEmployees> {
    /**
     * 分页的多表查询方法
     * @param
     * @param
     * @return
     */
    Page<HrEmployeesAll> myPage(Page<HrEmployeesAll> pageParam,
                                @Param(value = "hre") HrEmployeesQuery hrEmployeesQuery);

    int setStatus(@Param("em") HrEmployees hrEmployees);

    /**
     * 根据门店ID查询出职员ID，用于SysAccountImpl模块
     * @author 罗俊
     * @param brIds 区域ID
     * @return 职员ID
     */
    List<String> getIdsByBrIds(List<String> brIds);

    /**
     * 查询所有门店的领卡人
     * @param hrEmployees
     * @return
     */
    List<HrEmployees> queryName(HrEmployees hrEmployees);

}
