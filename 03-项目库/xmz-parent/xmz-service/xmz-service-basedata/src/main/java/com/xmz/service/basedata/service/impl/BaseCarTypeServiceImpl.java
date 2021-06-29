package com.xmz.service.basedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.serviceBase.MyQueryWrapper;
import com.xmz.service.basedata.entity.pojo.BaseCarType;
import com.xmz.service.basedata.entity.vo.CarTypeQueryParam;
import com.xmz.service.basedata.mapper.BaseCarTypeMapper;
import com.xmz.service.basedata.service.IBaseCarTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 车型 服务实现类
 * </p>
 *
 * @author weikang
 * @since 2021-06-02
 */
@Service
public class BaseCarTypeServiceImpl extends ServiceImpl<BaseCarTypeMapper, BaseCarType> implements IBaseCarTypeService {

    @Override
    public Page<BaseCarType> queryPage(Page<BaseCarType> page, CarTypeQueryParam queryParam) {
        QueryWrapper<BaseCarType> queryWrapper=new MyQueryWrapper<BaseCarType>()
                .eq("brand",queryParam.getBrand())
                .eq("car_Model",queryParam.getModel())
                .eq("car_Year",queryParam.getYear())
                .eq("displacement",queryParam.getCc());
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<BaseCarType> queryList(CarTypeQueryParam queryParam) {
        return baseMapper.selectData(queryParam);
    }

    @Override
    public List<BaseCarType> list2() {
        return baseMapper.list2();
    }

    @Override
    public List<BaseCarType> list3() {
        return baseMapper.list3();
    }

    @Override
    public List<BaseCarType> list4() {
        return baseMapper.list4();
    }

    @Override
    public List<BaseCarType> list5() {
        return baseMapper.list5();
    }

    @Override
    public List<BaseCarType> list6() {
        return baseMapper.list6();    }


}
