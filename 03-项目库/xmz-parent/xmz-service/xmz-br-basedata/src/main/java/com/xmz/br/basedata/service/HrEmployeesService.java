package com.xmz.br.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.basedata.pojo.entity.HrEmployees;
import com.xmz.br.basedata.pojo.vo.HrEmployeesPage;
import com.xmz.br.basedata.pojo.vo.HrEmployeesQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 员工信息 服务类
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-02
 */
public interface HrEmployeesService extends IService<HrEmployees> {

    String queryBrUuidByShop(String shop);

    Page<HrEmployeesPage> query(Page<HrEmployeesPage> hrEmployeesPagePage, @Param(value = "heq") HrEmployeesQuery hrEmployeesQuery);

    /**
     * 查询当天有几条数据
     * @return
     */
    int queryTodayCount();

    Page<HrEmployeesPage> queryBrandEmp(Page<HrEmployeesPage> page,@Param("tid")String tid,@Param("bid")String bid);


    /**
     *分页查询出还没有账号的员工
     * @return
     */
    public Page<HrEmployeesPage> queryNoAccEmps(Page<HrEmployeesPage> hrEmployeesPagePage,HrEmployeesQuery hrEmployeesQuery);
}
