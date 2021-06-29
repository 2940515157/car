package com.xmz.service.hr.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.HrBranches;
import com.xmz.service.hr.entity.vo.HrBranchesQuery;
import com.xmz.service.hr.entity.vo.MyHrBranches;
import com.xmz.service.hr.mapper.HrBranchesMapper;
import com.xmz.service.hr.service.IHrBranchesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 门店信息 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-04
 */
@Service
public class HrBranchesServiceImpl extends ServiceImpl<HrBranchesMapper, HrBranches> implements IHrBranchesService {

    @Autowired(required = false)
    HrBranchesMapper hrBranchesMapper;

    @Override
    public Page<MyHrBranches> queryAllPage(Page<MyHrBranches> pageParam,String brName) {
        return hrBranchesMapper.queryAllPage(pageParam,brName);
    }

    /**
     * 根据大区和区域查询出门店
     * @param hrBranches
     * @return
     */
    @Override
    public List<HrBranches> queryAllMenDian(HrBranches hrBranches) {
        return hrBranchesMapper.queryAllMenDian(hrBranches);
    }

    @Override
    public Page<MyHrBranches> query(Page<MyHrBranches> pageParam, HrBranchesQuery hrBrancherQuery) {
        return baseMapper.query(pageParam,hrBrancherQuery);
    }

    @Override
    public HrBranches getById(String brUuid) {
        return baseMapper.getById(brUuid);
    }

    @Override
    public List<HrBranches> queryAll() {
        return baseMapper.list();
    }
}
