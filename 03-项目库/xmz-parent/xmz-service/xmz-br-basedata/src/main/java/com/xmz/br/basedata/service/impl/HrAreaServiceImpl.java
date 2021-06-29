package com.xmz.br.basedata.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.basedata.mapper.HrAreaMapper;
import com.xmz.br.basedata.pojo.entity.HrArea;
import com.xmz.br.basedata.pojo.vo.HrAreaPage;
import com.xmz.br.basedata.pojo.vo.HrAreaQuery;
import com.xmz.br.basedata.service.HrAreaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 区域表 服务实现类
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-28
 */
@Service
public class HrAreaServiceImpl extends ServiceImpl<HrAreaMapper, HrArea> implements HrAreaService {


    @Override
    public Page<HrAreaPage> query(Page<HrAreaPage> hrAreaPagePage, HrAreaQuery hrAreaQuery) {
        return baseMapper.query(hrAreaPagePage,hrAreaQuery);
    }

    /**
     * 根据大区查出所有的区域
     * @param orgUuid
     * @return
     */
    @Override
    public List<HrArea> queryAllHrAreas(String orgUuid) {

        return baseMapper.queryAllHrAreas(orgUuid);
    }

    /**
     * 根据区域id查询出大区id
     * @param areaUuid
     * @return
     */
    @Override
    public HrArea queryAllOrgUuid(String areaUuid) {
        return baseMapper.queryAllOrgUuid(areaUuid);
    }
}
