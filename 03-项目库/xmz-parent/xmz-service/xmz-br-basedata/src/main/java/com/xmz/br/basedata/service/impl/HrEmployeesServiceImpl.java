package com.xmz.br.basedata.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.basedata.mapper.HrEmployeesMapper;
import com.xmz.br.basedata.pojo.entity.HrEmployees;
import com.xmz.br.basedata.pojo.vo.HrEmployeesPage;
import com.xmz.br.basedata.pojo.vo.HrEmployeesQuery;
import com.xmz.br.basedata.service.HrEmployeesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-02
 */
@Service
public class HrEmployeesServiceImpl extends ServiceImpl<HrEmployeesMapper, HrEmployees> implements HrEmployeesService {

    @Override
    public String queryBrUuidByShop(String shop) {
        return baseMapper.queryBrUuidByShop(shop);
    }

    @Override
    public Page<HrEmployeesPage> query(Page<HrEmployeesPage> hrEmployeesPagePage, HrEmployeesQuery hrEmployeesQuery) {
        return baseMapper.query(hrEmployeesPagePage,hrEmployeesQuery);
    }

    @Override
    public int queryTodayCount() {
        return baseMapper.queryTodayCount();
    }

    @Override
    public Page<HrEmployeesPage> queryBrandEmp(Page<HrEmployeesPage> page, String tid, String bid) {
        return baseMapper.queryBrandEmp(page,tid,bid);
    }

    /**
     * 查询出还没有账号的员工
     * @return
     */
    @Override
    public Page<HrEmployeesPage> queryNoAccEmps(Page<HrEmployeesPage> hrEmployeesPagePage,HrEmployeesQuery hrEmployeesQuery) {
        return baseMapper.queryNoAccEmps(hrEmployeesPagePage,hrEmployeesQuery);
    }
}
