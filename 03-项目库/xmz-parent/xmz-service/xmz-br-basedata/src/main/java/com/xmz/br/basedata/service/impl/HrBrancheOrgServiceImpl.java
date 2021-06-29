package com.xmz.br.basedata.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.basedata.mapper.HrBrancheOrgMapper;
import com.xmz.br.basedata.pojo.entity.HrBrancheOrg;
import com.xmz.br.basedata.pojo.vo.MyHrBrancheOrg;
import com.xmz.br.basedata.pojo.vo.QueryHrBrancheOrg;
import com.xmz.br.basedata.service.HrBrancheOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 车间信息 服务实现类
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-31
 */
@Service
public class HrBrancheOrgServiceImpl extends ServiceImpl<HrBrancheOrgMapper, HrBrancheOrg> implements HrBrancheOrgService {


    @Autowired(required = false)
    HrBrancheOrgMapper hrBrancheOrgMapper;

    @Override
    public Page<MyHrBrancheOrg> queryBrancheOrg(Page<MyHrBrancheOrg> page, QueryHrBrancheOrg queryHrBrancheOrg) {

        Page<MyHrBrancheOrg> aa = hrBrancheOrgMapper.queryBrancheOrg(page, queryHrBrancheOrg);

        return aa;
    }
}
