package com.xmz.br.basedata.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.basedata.mapper.HrBranchesMapper;
import com.xmz.br.basedata.pojo.entity.HrBranches;
import com.xmz.br.basedata.pojo.vo.HrBranchesPage;
import com.xmz.br.basedata.pojo.vo.HrBranchesQuery;
import com.xmz.br.basedata.pojo.vo.MyHrBranches;
import com.xmz.br.basedata.service.HrBranchesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 门店信息 服务实现类
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-28
 */
@Service
public class HrBranchesServiceImpl extends ServiceImpl<HrBranchesMapper, HrBranches> implements HrBranchesService {

    @Override
    public MyHrBranches queryMyHrBranches(String brCode) {
        return baseMapper.queryMyHrBranches(brCode);
    }


    @Override
    public void query(Page<HrBranchesPage> hrBranchesPagePage, HrBranchesQuery hrBranchesQuery) {
        baseMapper.query(hrBranchesPagePage,hrBranchesQuery);
    }

    @Override
    public Page<HrBranchesPage> noPageQuery(Page<HrBranchesPage> pageParams,String[] brCode, String brName) {
        return baseMapper.noPageQuery(pageParams,brCode,brName);
    }

    @Override
    public Page<HrBranchesPage> pageQuery(Page<HrBranchesPage> pageParams, String brName) {
        return baseMapper.pageQuery(pageParams,brName);
    }

    @Override
    public Page<HrBranchesPage> queryUnAuthorizationBranches(Page<HrBranchesPage> hrBranchesPagePage, HrBranchesQuery hrBranchesQuery) {
        return baseMapper.queryUnAuthorizationBranches(hrBranchesPagePage,hrBranchesQuery);
    }

    @Override
    public Page<HrBranchesPage> queryAuthorizationBranches(Page<HrBranchesPage> hrBranchesPagePage, HrBranchesQuery hrBranchesQuery) {
        return baseMapper.queryAuthorizationBranches(hrBranchesPagePage,hrBranchesQuery);
    }

    /**
     * 根据大区和区域查询出门店
     * @param
     * @return
     */
    @Override
    public List<HrBranches> queryAllMenDian(String areaUuid) {
        return baseMapper.queryAllMenDian(areaUuid);
    }
}
