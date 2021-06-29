package com.xmz.br.basedata.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.basedata.mapper.HrTeamsEmpMapper;
import com.xmz.br.basedata.pojo.entity.HrTeamsEmp;
import com.xmz.br.basedata.pojo.vo.MyHrTeamsEmp;
import com.xmz.br.basedata.service.HrTeamsEmpService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 班组成员信息 服务实现类
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-08
 */
@Service
public class HrTeamsEmpServiceImpl extends ServiceImpl<HrTeamsEmpMapper, HrTeamsEmp> implements HrTeamsEmpService {

    @Override
    public Page<MyHrTeamsEmp> queryTeamUuId(Page<MyHrTeamsEmp> page, String id) {
        return baseMapper.queryTeamUuId(page,id);
    }
}
