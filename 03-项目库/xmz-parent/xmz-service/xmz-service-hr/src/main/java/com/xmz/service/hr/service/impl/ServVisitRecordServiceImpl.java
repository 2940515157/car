package com.xmz.service.hr.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.*;
import com.xmz.service.hr.entity.vo.HrBranchesQuery;
import com.xmz.service.hr.entity.vo.ServVisitRecordQuery;
import com.xmz.service.hr.mapper.ServVisitRecordMapper;
import com.xmz.service.hr.service.IServVisitRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 回访记录 服务实现类
 * </p>
 *
 * @author hwb
 * @since 2021-06-04
 */
@Service
public class ServVisitRecordServiceImpl extends ServiceImpl<ServVisitRecordMapper, HuiFang> implements IServVisitRecordService {

    @Override
    public Page<HuiFang> myPage(Page<HuiFang> pageParam, ServVisitRecordQuery servVisitRecordQuery) {
        return baseMapper.myPage(pageParam, servVisitRecordQuery);
    }

    @Override
    public List<HrOrg> listAll() {
        return baseMapper.listAll();
    }

    @Override
    public List<HrArea> arealistAll(HrOrg hrOrg) {
        return baseMapper.arealistAll(hrOrg);
    }

    @Override
    public List<HrBranches> brancheslistAll(HrBranchesQuery hrBranchsQuery) {
        return baseMapper.brancheslistAll(hrBranchsQuery);
    }
}
