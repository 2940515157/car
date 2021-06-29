package com.xmz.br.basedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.basedata.pojo.entity.HrEmployees;
import com.xmz.br.basedata.pojo.vo.HrEmployeesPage;
import com.xmz.br.basedata.pojo.vo.HrEmployeesQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 员工信息 Mapper 接口
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-02
 */
public interface HrEmployeesMapper extends BaseMapper<HrEmployees> {
    String queryBrUuidByShop(String shop);

    Page<HrEmployeesPage> query(Page<HrEmployeesPage> hrEmployeesPagePage, @Param(value = "heq") HrEmployeesQuery hrEmployeesQuery);

    /**
     * 查询当天有几条数据
     * @return
     */
    int queryTodayCount();

    Page<HrEmployeesPage> queryBrandEmp(Page<HrEmployeesPage> page, @Param("tid") String tid,@Param("bid") String bid);

    /**
     *分页查询出还没有账号的员工
     * @return
     */
    public Page<HrEmployeesPage> queryNoAccEmps(Page<HrEmployeesPage> hrEmployeesPagePage,@Param("he")HrEmployeesQuery hrEmployeesQuery);

}
