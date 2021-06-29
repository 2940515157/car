package com.xmz.service.hr.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.HrArea;
import com.xmz.service.hr.entity.pojo.HrAreaPage;
import com.xmz.service.hr.entity.pojo.HrOrg;
import com.xmz.service.hr.entity.vo.HrAreaQuery;
import com.xmz.service.hr.mapper.HrAreaMapper;
import com.xmz.service.hr.service.IHrAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 区域表 服务实现类
 * </p>
 *
 * @author lm
 * @since 2021-06-01
 */
@Service
public class HrAreaServiceImpl extends ServiceImpl<HrAreaMapper, HrArea> implements IHrAreaService {

    @Autowired(required = false)
    HrAreaMapper hrAreaMapper;

    @Override
    public Page<HrAreaPage> myPage(Page<HrAreaPage> pageParam, HrAreaQuery hrAreaQuery) {
        return baseMapper.myPage(pageParam,hrAreaQuery);
    }

    @Override
    public List<HrOrg> listAll() {
        return baseMapper.listAll();
    }

    @Override
    public List<HrArea> queryAllHrAreas(String orgUuid) {
        return hrAreaMapper.queryAllHrAreas(orgUuid);
    }

    /**
     * 根据大区查出所有的区域
     * @param id
     * @return
     */
    @Override
    public List<HrArea> queryallQuYu(String id) {
        return baseMapper.queryallQuYu(id);
    }

    /**
     * 根据区域id查询出大区id
     * @param areaUuid
     * @return
     */
    @Override
    public HrArea queryAllOrgUuid(String areaUuid) {
        return hrAreaMapper.queryAllOrgUuid(areaUuid);
    }


}
