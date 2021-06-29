package com.xmz.br.basedata.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.basedata.mapper.HrBrancheTeamsMapper;
import com.xmz.br.basedata.pojo.entity.HrBrancheTeams;
import com.xmz.br.basedata.pojo.vo.MyHrBrancheTeams;
import com.xmz.br.basedata.pojo.vo.QueryHrBrancheTeams;
import com.xmz.br.basedata.service.HrBrancheTeamsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 班组信息 服务实现类
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-03
 */
@Service
public class HrBrancheTeamsServiceImpl extends ServiceImpl<HrBrancheTeamsMapper, HrBrancheTeams> implements HrBrancheTeamsService {


    @Override
    public Page<MyHrBrancheTeams> queryBrancheTeams(Page page, QueryHrBrancheTeams queryHrBrancheTeams) {


        return baseMapper.queryBrancheTeams(page,queryHrBrancheTeams);
    }
}
