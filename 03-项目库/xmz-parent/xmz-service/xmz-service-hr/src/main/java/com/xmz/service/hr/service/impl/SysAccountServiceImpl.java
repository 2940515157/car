package com.xmz.service.hr.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.xmz.service.hr.entity.pojo.SysAccount;
import com.xmz.service.hr.entity.vo.SysAccountQuery;
import com.xmz.service.hr.entity.vo.SysRoleGrantVo;
import com.xmz.service.hr.mapper.*;
import com.xmz.service.hr.service.ISysAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-10
 */
@Service
public class SysAccountServiceImpl extends ServiceImpl<SysAccountMapper, SysAccount> implements ISysAccountService {

    @Autowired(required = false)
    SysAccountMapper sysAccountMapper;

    /**
     * 查询所有领卡人
     *
     * @return
     */
    @Override
    public List<SysAccount> queryAllSa() {
        return sysAccountMapper.queryAllSa();
    }

    @Resource
    HrAreaMapper hrAreaMapper;
    @Resource
    HrBranchesMapper hrBranchesMapper;
    @Resource
    HrEmployeesMapper hrEmployeesMapper;


    @Override
    public Page<Map<String, Object>> queryPage(Page<Map<String, Object>> page, SysAccountQuery accountQuery) {
        List<String> empIds = null;
        String brId = accountQuery.getBrId();
        if (StringUtils.isNullOrEmpty(brId)) {
            String areaId = accountQuery.getAreaId();
            if (StringUtils.isNullOrEmpty(areaId)) {
                String orgId = accountQuery.getOrgId();
                if (!StringUtils.isNullOrEmpty(orgId)) {
                    empIds = getEmpIdsByOrgId(orgId);
                }
            } else {
                empIds = getEmpIdsByAreaIds(Collections.singletonList(areaId));
            }
        } else {
            empIds = getEmpIdsByBrIds(Collections.singletonList(brId));
        }
        if(empIds!=null && CollectionUtils.isEmpty(empIds)) return page;

        accountQuery.setEmpIds(empIds);
        System.out.println(accountQuery.toString());
        baseMapper.queryPage(page, accountQuery);
        return page;
    }

    @Override
    public boolean addAccount(SysAccount account) {
        account.setPassword("123456");
        account.setPasswordInitFlag("Y");
        return save(account);
    }

    @Override
    public Page<SysRoleGrantVo> infiniteList(Page<SysRoleGrantVo> page, String accountId, String name) {
        return baseMapper.infiniteList(page, accountId, name);
    }

    List<String> getEmpIdsByBrIds(List<String> brIds) {
        return hrEmployeesMapper.getIdsByBrIds(brIds);
    }

    List<String> getEmpIdsByAreaIds(List<String> areaId) {
        List<String> brIds = hrBranchesMapper.getIdsByAreaIds(areaId);
        if(CollectionUtils.isEmpty(areaId)) return brIds;
        return getEmpIdsByBrIds(brIds);
    }

    List<String> getEmpIdsByOrgId(String orgId) {
        List<String> areaIds = hrAreaMapper.getIdsByOrgId(orgId);
        if(CollectionUtils.isEmpty(areaIds)) return areaIds;
        return getEmpIdsByAreaIds(areaIds);
    }

}
