package com.xmz.service.hr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.serviceBase.MyQueryWrapper;
import com.xmz.service.hr.entity.pojo.HrOrg;
import com.xmz.service.hr.entity.vo.HrOrgQuery;
import com.xmz.service.hr.mapper.HrOrgMapper;
import com.xmz.service.hr.service.IHrOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 部门信息 服务实现类
 * </p>
 *
 * @author leiyaping
 * @since 2021-05-28
 */
@Service
public class HrOrgServiceImpl extends ServiceImpl<HrOrgMapper, HrOrg> implements IHrOrgService {

    @Autowired(required = false)
    HrOrgMapper hrOrgMapper;

    @Override
    public Page<HrOrg> pageQuery(Page<HrOrg> pagePram, HrOrgQuery hrOrgQuery) {
        //创建条件对象
        QueryWrapper<HrOrg> queryWrapper=new MyQueryWrapper<HrOrg>()

                .like("ORG_NO",hrOrgQuery.getOrgNo())
                .like("ORG_NAME",hrOrgQuery.getOrgName())
                .eq("IS_DISABLED",hrOrgQuery.getIsDisabled());
       return baseMapper.selectPage(pagePram,queryWrapper);
    }

    @Override
    public Page<HrOrg> queryAllPage(Page<HrOrg> pageParam) {

        return hrOrgMapper.queryAllPage(pageParam);
    }

    /**
     * 查询所有营运中心的部门
     * @return
     */
    @Override
    public List<HrOrg> queryAllYingYun(String orgUuid) {
        return hrOrgMapper.queryAllYingYun( orgUuid);
    }
}
